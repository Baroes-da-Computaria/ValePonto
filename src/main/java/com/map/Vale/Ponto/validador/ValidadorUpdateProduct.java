package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.EntityExistsException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.product.ProductRequestDTO;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import com.map.Vale.Ponto.repositories.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUpdateProduct {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public ValidadorUpdateProduct(
            ProductRepository productRepository,
            CompanyRepository companyRepository
    ) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    public void validar(String productName, Long id_company, ProductRequestDTO dto) {
        validarExistenciaCompany(id_company);
        validarExistenciaProductNaCompany(productName, id_company);
    }
    private void validarExistenciaCompany(Long companyId) {
        companyRepository.findById(companyId).orElseThrow(() ->
                new ResourceNotFoundException("Company com id " + companyId + " não encontrada.")
        );
    }

    private void validarExistenciaProductNaCompany(String productName, Long companyId) {
        if (!productRepository.existsByNameIgnoreCaseAndCompanyId(productName, companyId)) {
            throw new EntityExistsException("Product " + productName + " não existe na company informada.");
        }
    }
}
