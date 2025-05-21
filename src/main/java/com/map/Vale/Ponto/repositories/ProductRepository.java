package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}