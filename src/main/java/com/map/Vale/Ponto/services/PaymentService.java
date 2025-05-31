package com.map.Vale.Ponto.services;

import com.fasterxml.jackson.databind.ObjectReader;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.model.command.ConfirmPayment;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.model.payments.*;
import com.map.Vale.Ponto.repositories.OrderRepository;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentStrategyFactory strategyFactory;
    private final PaymentRepository paymentRepository;
    private final ConfirmPayment confirmPayment;

    public PaymentService(
            PaymentStrategyFactory strategyFactory,
            PaymentRepository paymentRepository,
            ConfirmPayment confirmPayment
    ) {
        this.strategyFactory = strategyFactory;
        this.paymentRepository = paymentRepository;
        this.confirmPayment = confirmPayment;
    }

    public void process(PaymentRequestDTO dto) {
        PaymentStrategy strategy = strategyFactory.getStrategy(dto.getPaymentMethods());
        strategy.processPayment(dto);
    }

    public PaymentDetailsDTO findById(Long id) {
        var payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment não encontrado com id: " + id));
        return new PaymentDetailsDTO(payment);

    }

    public void confirmPayment(Long orderId){
        confirmPayment.execute(orderId);
    }
}