package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.product.ProductDTO;
import com.map.Vale.Ponto.model.product.Product;
import com.map.Vale.Ponto.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    public Product save(ProductDTO dto) {
        Product product = fromDTO(dto);
        return productRepository.save(product);
    }

    public Product update(Long id, ProductDTO dto){
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory().name());
        entity.setDescription(dto.getDescription());
        entity.setSubtitle(dto.getSubtitle());
        entity.setPrice(dto.getPrice());
        entity.setImageURL(dto.getImageURL());

        // Atualiza a pontuação com base no novo preço
        entity.setPoints((int)(dto.getPrice() / 10));

        return productRepository.save(entity);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private Product fromDTO(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory().name());
        product.setDescription(dto.getDescription());
        product.setSubtitle(dto.getSubtitle());
        product.setPrice(dto.getPrice());
        product.setImageURL(dto.getImageURL());
        product.setPoints((int)(dto.getPrice() / 10));
        return product;
    }
}
