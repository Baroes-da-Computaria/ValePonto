package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.enums.PaymentStatus;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import com.map.Vale.Ponto.services.OrderService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("CREDIT_CARD")
public class CreditCardPaymentStrategy implements PaymentStrategy {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public CreditCardPaymentStrategy(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    @Override
    public void processPayment(PaymentRequestDTO dto) {
        var order = orderService.createBuilder(dto.getClient_id(), dto.getAddressForOrder(), dto.getProductIdToQuantity());
        process(order, dto.getCardInfo());
    }

    private void process(Order order, CreditCardInfoDTO cardInfo) {

        // Validação básica
        if (!cardInfo.getCardNumber().matches("\\d{16}")) {
            throw new IllegalArgumentException("Número do cartão inválido");
        }

        // Simulação de autorização
        boolean autorizado = simularAutorizacao(cardInfo);

        if (!autorizado) {
            throw new RuntimeException("Transação recusada pela operadora");
        }

        // Cria o pagamento
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(PaymentMethods.CREDIT_CARD);
        payment.setAmount(order.getTotal());
        payment.setStatus(PaymentStatus.CONFIRMED);
        payment.setPaidAt(LocalDateTime.now());

        paymentRepository.save(payment);

    }

    private boolean simularAutorizacao(CreditCardInfoDTO card) {
        // Simulação simples: se CVV termina com par, autoriza
        return Integer.parseInt(card.getCvv()) % 2 == 0;
    }

}