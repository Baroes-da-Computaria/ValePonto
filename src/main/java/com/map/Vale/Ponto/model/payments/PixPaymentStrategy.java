package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.enums.PaymentStatus;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.repositories.OrderRepository;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component("PIX")
public class PixPaymentStrategy implements PaymentStrategy {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PixPaymentStrategy(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public void processPayment(Order order) {

        // Simula criação do QR Code PIX
        String pixCode = gerarPixCode(order.getId(), order.getTotal());

        // Cria e associa pagamento
        Payment payment = new Payment();
        payment.setOrder(orderRepository.findById(order.getId()).get());
        payment.setMethod(PaymentMethods.PIX);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setAmount(order.getTotal());
        payment.setPixCode(pixCode); // campo extra específico do PIX

        // Salva no banco
        paymentRepository.save(payment);

        System.out.println("Pagamento via PIX criado. QR Code: " + pixCode);
    }

    private String gerarPixCode(Long orderId, BigDecimal amount) {
        // Exemplo simples de QR code (poderia usar lib como gerencianet, pix4j, etc)
        return "00020126360014BR.GOV.BCB.PIX0114+5531999999995204000053039865404"
                + amount.setScale(2)
                + "5802BR5913Loja Exemplo6009SAO PAULO62070503***6304"
                + UUID.randomUUID().toString().substring(0, 4);
    }

}
