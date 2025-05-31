package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.model.order.Order;
import org.springframework.stereotype.Component;

@Component("CREDIT_CARD")
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(Order order) {
        System.out.println("Processando pagamento via Cartão de Crédito para pedido " + order.getId());
        //todo: lógica específica...
    }
}
