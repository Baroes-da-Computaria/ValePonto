package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.model.product.TopSoldProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
    SELECT new com.map.Vale.Ponto.model.product.TopSoldProductDTO(
        p.id,
        p.name,
        p.category,
        p.description,
        p.price,
        p.imageURL,
        p.subtitle,
        p.points,
        SUM(oi.quantity)
    )
    FROM Order o
    JOIN o.items oi
    JOIN oi.product p
    JOIN o.payment pay
    WHERE pay.status = 'CONFIRMED'
    GROUP BY p.id, p.name, p.category, p.description, p.price, p.imageURL, p.subtitle, p.points
    ORDER BY SUM(oi.quantity) DESC
""")
    Page<TopSoldProductDTO> findTopSoldProducts(Pageable pageable);
}