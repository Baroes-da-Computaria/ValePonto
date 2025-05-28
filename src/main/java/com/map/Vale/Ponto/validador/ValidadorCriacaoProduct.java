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

        // se não existir uma company com esse id não podemos criar um product
        // a company deve estar cadastrado previamente no banco de dados
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException(
                    "Company com id " + companyId + " não encontrado.");
        }

    }

    private void validarExistenciaProductNaCompany(String productName, Long companyId) {

        // todo: terminar a query
        // se existir um product com esse nome na company informada
        if (productRepository.existsByNameAndCompanyId(productName, companyId)) {
            throw new EntityExistsException("Product " + productName + " já existe na company informada.");
        }

    }
}
