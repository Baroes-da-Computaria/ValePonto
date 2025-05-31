package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.model.order.Order;
import org.springframework.stereotype.Component;

@Component("PIX")
public class PixPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(Order order) {
        System.out.println("Processando pagamento via PIX para pedido " + order.getId());
        //todo: lógica específica...
    }
}
