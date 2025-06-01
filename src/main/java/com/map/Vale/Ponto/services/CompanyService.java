package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.company.Company;
import com.map.Vale.Ponto.model.company.CompanyRequestDTO;
import com.map.Vale.Ponto.model.company.CompanyResponseDTO;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import com.map.Vale.Ponto.validador.ValidadorCompany;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ValidadorCompany validadorCompany;

    public CompanyService(CompanyRepository companyRepository, ValidadorCompany validadorCompany) {
        this.companyRepository = companyRepository;
        this.validadorCompany = validadorCompany;
    }

    @Transactional(readOnly = true)
    public CompanyResponseDTO findById(Long id) {
        var company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company não encontrado com id: " + id));
        return new CompanyResponseDTO(company);
    }

    @Transactional(readOnly = true)
    public Page<CompanyResponseDTO> findAll(PageRequest pageable) {
        return companyRepository.findAll(pageable).map(CompanyResponseDTO::new);
    }

    @Transactional
    public CompanyResponseDTO save(CompanyRequestDTO dto) {
        var company = new Company(dto);
        validadorCompany.validar(company, new Address(dto.getAddress()));
        company.getAddress().setCompany(company);
        var saved = companyRepository.save(company);
        return new CompanyResponseDTO(saved);
    }

    @Transactional
    public CompanyResponseDTO update(Long id, CompanyRequestDTO dto) {
        var company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company não encontrado com id: " + id));
        company.updateFromRequest(dto);
        validadorCompany.validar(company, new Address(dto.getAddress()));
        var updated = companyRepository.save(company);
        return new CompanyResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company não encontrado com id: " + id);
        }
        try {
            companyRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }
}
