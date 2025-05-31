package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.model.order.Order;

public interface PaymentStrategy {
    void processPayment(Order order);
}

