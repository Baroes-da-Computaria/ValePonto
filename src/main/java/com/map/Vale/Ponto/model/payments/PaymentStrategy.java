package com.map.Vale.Ponto.model.payments;

public interface PaymentStrategy {
    void processPayment(PaymentRequestDTO dto);
}

