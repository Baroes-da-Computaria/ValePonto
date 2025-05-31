package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.model.payments.PaymentStrategy;
import com.map.Vale.Ponto.model.payments.PaymentStrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentStrategyFactory strategyFactory;

    public PaymentService(PaymentStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public void process(Order order, PaymentMethods paymentMethods) {

        PaymentStrategy strategy = strategyFactory.getStrategy(paymentMethods);
        strategy.processPayment(order);

    }
}
