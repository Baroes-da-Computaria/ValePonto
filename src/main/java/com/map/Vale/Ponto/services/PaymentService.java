package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.model.payments.PaymentDetailsDTO;
import com.map.Vale.Ponto.model.payments.PaymentStrategy;
import com.map.Vale.Ponto.model.payments.PaymentStrategyFactory;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentStrategyFactory strategyFactory;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentStrategyFactory strategyFactory, PaymentRepository paymentRepository) {
        this.strategyFactory = strategyFactory;
        this.paymentRepository = paymentRepository;
    }

    public void process(Order order, PaymentMethods paymentMethods) {

        PaymentStrategy strategy = strategyFactory.getStrategy(paymentMethods);
        strategy.processPayment(order);

    }

    public PaymentDetailsDTO findById(Long id) {
        var payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment não encontrado com id: " + id));
        return new PaymentDetailsDTO(payment);
    }
}
