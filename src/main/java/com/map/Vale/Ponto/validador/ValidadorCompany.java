package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.EntityExistsException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.company.Company;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import com.map.Vale.Ponto.validador.cep.ViaCepValidator;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCompany {

    private final CompanyRepository companyRepository;
    private final ViaCepValidator viaCepValidator;

    public ValidadorCompany(
            CompanyRepository companyRepository,
            ViaCepValidator viaCepValidator
    ){
        this.companyRepository = companyRepository;
        this.viaCepValidator = viaCepValidator;
    }

    public void validar(Company company, Address address) {
        validarExistenciaCompany(company.getName(),company.getCnpj());
        validarExistenciaCep(address.getCep());
    }

    private void validarExistenciaCep(String cep) {
        if (!viaCepValidator.isValid(cep)) {
            throw new ResourceNotFoundException("CEP " + cep + " não encontrado");
        }
    }

    private void validarExistenciaCompany(String name, String cnpj) {
        if(companyRepository.existsByName(name)){
            throw new EntityExistsException(
                    "Company com nome " + name + " já existe.");
        }
        if(companyRepository.existsByCnpj(cnpj)){
            throw new EntityExistsException(
                    "Company com CNPJ " + cnpj + " já existe.");
        }
    }
}
