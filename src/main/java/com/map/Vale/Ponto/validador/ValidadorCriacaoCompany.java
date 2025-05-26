package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.company.Company;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCriacaoCompany {

    private final CompanyRepository companyRepository;

    public ValidadorCriacaoCompany(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }


    public void validar(Company company, Address address) {
        validarExistenciaCompany(company.getName());
        validarExistenciaCep(address.getCep());
    }

    private void validarExistenciaCep(String cep) {
        // verifica a existencia do cep
        // todo : buscar na api dos correios
    }

    private void validarExistenciaCompany(String name) {
        // Não pode ter duas empresas com mesmo nome
        if(companyRepository.existsByName(name)){
            throw new ResourceNotFoundException(
                    "Company com nome " + name + " já existe.");
        }
    }
}
