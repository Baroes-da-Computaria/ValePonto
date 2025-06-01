package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentStrategyFactory {

    private final Map<String, PaymentStrategy> strategies;

    public PaymentStrategyFactory(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
    }

    public PaymentStrategy getStrategy(PaymentMethods methods) {
        PaymentStrategy strategy = strategies.get(methods.name());
        if (strategy == null) {
            throw new IllegalArgumentException("Método de pagamento não suportado: " + methods.name());
        }
        return strategy;
    }
}

