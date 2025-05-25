package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

//    @Query("SELECT p FROM Product p WHERE p.name = ?1 AND p.company.id = ?2")
//    boolean existsThisProductInCompany(String productName, Long companyId);

    boolean existsByNameAndCompanyId(String name, Long companyId);

}