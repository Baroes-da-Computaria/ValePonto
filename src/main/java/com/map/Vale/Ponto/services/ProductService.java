package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.product.Product;
import com.map.Vale.Ponto.model.product.ProductRequestDTO;
import com.map.Vale.Ponto.model.product.ProductResponseDTO;
import com.map.Vale.Ponto.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseDTO::new);
    }

    public ProductResponseDTO findById(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        return new ProductResponseDTO(product);
    }

    public ProductResponseDTO save(ProductRequestDTO dto) {
        var product = new Product(dto);
        var saved = productRepository.save(product);
        return new ProductResponseDTO(saved);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        product.updateFromRequest(dto);
        var updated = productRepository.save(product);
        return new ProductResponseDTO(updated);
    }

    public void delete(Long id) {

        // verifica se esse product existe
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com id: " + id);
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }

    }
}
