package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.company.Company;
import com.map.Vale.Ponto.model.product.*;
import com.map.Vale.Ponto.repositories.CompanyRepository;
import com.map.Vale.Ponto.repositories.OrderRepository;
import com.map.Vale.Ponto.repositories.ProductRepository;
import com.map.Vale.Ponto.validador.ValidadorCriacaoProduct;

import com.map.Vale.Ponto.validador.ValidadorUpdateProduct;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final ValidadorCriacaoProduct validadorCriacaoProduct;
    private final ValidadorUpdateProduct validadorUpdateProduct;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository,
                          CompanyRepository companyRepository,
                          ValidadorCriacaoProduct validadorCriacaoProduct,
                          ValidadorUpdateProduct validadorUpdateProduct,
                            OrderRepository orderRepository
    ) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.validadorCriacaoProduct = validadorCriacaoProduct;
        this.validadorUpdateProduct = validadorUpdateProduct;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
        return new ProductResponseDTO(product);
    }

    @Transactional
    public ProductDetailsDTO save(ProductRequestDTO dto) {
        var product = new Product(dto);
        validadorCriacaoProduct.validar(product, dto.getCompanyId());
        Company company = companyRepository.getReferenceById(dto.getCompanyId());
        product.setCompany(company);
        company.getProducts().add(product);
        var saved = productRepository.save(product);
        return new ProductDetailsDTO(saved);
    }

    @Transactional
    public ProductResponseDTO update(Long productId, Long companyId, ProductRequestDTO dto) {
        var product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + productId));
        validadorUpdateProduct.validar(product.getName(), companyId, dto);
        product.updateFromRequest(dto);
        var updated = productRepository.save(product);
        return new ProductResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com id: " + id);
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

    public Page<TopSoldProductDTO> findTopSoldProducts(
            String category,
            Double minPrice,
            Double maxPrice,
            Pageable pageable
    ) {
        validaTopSoldProduts(minPrice, maxPrice);
        return orderRepository.findTopSoldProducts(pageable);
    }
    public Page<ProductSalesView> getTopSoldProducts(
            String category,
            Double minPrice,
            Double maxPrice,
            Pageable pageable
    ) {
        validaTopSoldProduts(minPrice, maxPrice);
        return productRepository.getTopSoldProducts(category, minPrice, maxPrice, pageable);
    }

    private void validaTopSoldProduts(Double minPrice, Double maxPrice) {
        if (minPrice != null && minPrice < 0) {
            throw new IllegalArgumentException("O preço mínimo não pode ser negativo");
        }
        if (maxPrice != null && maxPrice < 0) {
            throw new IllegalArgumentException("O preço máximo não pode ser negativo");
        }
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            throw new IllegalArgumentException("O preço mínimo não pode ser maior que o preço máximo");
        }
    }

    private TopSoldProductDTO mapToTopSoldProductDTO(Object[] result) {
        Product product = (Product) result[0];
        Long totalSold = (Long) result[1];

        return new TopSoldProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getImageURL(),
                product.getSubtitle(),
                product.getPoints(),
                totalSold
        );
    }

    
}
