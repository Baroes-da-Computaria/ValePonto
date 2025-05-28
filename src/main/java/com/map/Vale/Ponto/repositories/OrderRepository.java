package com.map.Vale.Ponto.repositories;

import com.map.Vale.Ponto.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}