package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.company.Company;
import com.map.Vale.Ponto.model.company.CompanyRequestDTO;
import com.map.Vale.Ponto.model.company.CompanyResponseDTO;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import com.map.Vale.Ponto.validador.ValidadorCriacaoCompany;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ValidadorCriacaoCompany validatorCriacaoCompany;

    public CompanyService(CompanyRepository companyRepository, ValidadorCriacaoCompany validadorCriacaoCompany) {
        this.companyRepository = companyRepository;
        this.validatorCriacaoCompany = validadorCriacaoCompany;
    }

    public CompanyResponseDTO findById(Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company não encontrado com id: " + id));

        return new CompanyResponseDTO(company);
    }

    public Page<CompanyResponseDTO> findAll(PageRequest pageable) {
        return companyRepository.findAll(pageable).map(CompanyResponseDTO::new);
    }

    public CompanyResponseDTO save(CompanyRequestDTO dto) {
        // transforma de dto para entidade
        var company = new Company(dto);

        // validacao da criação da company
        validatorCriacaoCompany.validar(company, dto.getAddress());

        // Associa o address a comapany(consistencia na persistencia)
        company.getAddress().setCompany(company);


        var saved = companyRepository.save(company);
        return new CompanyResponseDTO(saved);
    }

    public CompanyResponseDTO update(Long id, CompanyRequestDTO dto) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company não encontrado com id: " + id));

        company.updateFromRequest(dto);
        var updated = companyRepository.save(company);
        return new CompanyResponseDTO(updated);
    }

    public void delete(Long id) {

        // verifica se esse client existe
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
