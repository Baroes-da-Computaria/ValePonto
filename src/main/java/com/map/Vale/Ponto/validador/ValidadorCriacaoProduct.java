package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.EntityExistsException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.product.Product;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import com.map.Vale.Ponto.repositories.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCriacaoProduct {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public ValidadorCriacaoProduct(ProductRepository productRepository, CompanyRepository companyRepository) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    public void validar(Product product, Long companyId) {
        validarExistenciaCompany(companyId);
        validarExistenciaProductNaCompany(product.getName(), companyId);
    }

    private void validarExistenciaCompany(Long companyId) {
        companyRepository.findById(companyId).orElseThrow(() ->
            new ResourceNotFoundException("Company com id " + companyId + " não encontrada.")
        );
    }

    private void validarExistenciaProductNaCompany(String productName, Long companyId) {
        if (productRepository.existsByNameIgnoreCaseAndCompanyId(productName, companyId)) {
            throw new EntityExistsException("Product " + productName + " já existe na company informada.");
        }
    }
}
