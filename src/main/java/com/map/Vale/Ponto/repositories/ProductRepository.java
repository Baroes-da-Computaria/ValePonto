package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.product.Product;
import com.map.Vale.Ponto.model.product.ProductSalesView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameIgnoreCaseAndCompanyId(String name, Long companyId);

    @Query("SELECT p.id AS id, p.name AS name, p.category AS category, p.description AS description, " +
            "p.price AS price, p.imageURL AS imageURL, p.subtitle AS subtitle, p.points AS points, " +
            "SUM(oi.quantity) AS totalSold " +
            "FROM Product p " +
            "JOIN p.items oi " +
            "WHERE (:category IS NULL OR p.category = :category) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND oi.order.payment.status = 'CONFIRMED' " +
            "GROUP BY p.id, p.name, p.category, p.description, p.price, p.imageURL, p.subtitle, p.points " +
            "ORDER BY totalSold DESC")
    Page<ProductSalesView> getTopSoldProducts(
            @Param("category") String category,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable);


}