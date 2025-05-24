package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.company.Company;
import com.map.Vale.Ponto.model.company.CompanyRequestDTO;
import com.map.Vale.Ponto.model.company.CompanyResponseDTO;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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
        var company = new Company(dto);
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
