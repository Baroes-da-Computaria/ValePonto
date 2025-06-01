package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import com.map.Vale.Ponto.services.OrderService;
import com.map.Vale.Ponto.services.PointService;
import org.springframework.stereotype.Component;


@Component("CREDIT_CARD")
public class CreditCardPaymentStrategy implements PaymentStrategy {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;
    private final PointService pointService;
    private final CalcularValorCompra calcularValorCompra;

    public CreditCardPaymentStrategy(
            PaymentRepository paymentRepository,
            OrderService orderService,
            PointService pointService,
            CalcularValorCompra calcularValorCompra
    ) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
        this.pointService = pointService;
        this.calcularValorCompra = calcularValorCompra;
    }

    @Override
    public void processPayment(PaymentRequestDTO dto) {
        var order = orderService.createBuilder(dto.getClient_id(), dto.getAddress(), dto.getProductIdToQuantity());
        process(order, dto.getCardInfo(), dto.getPoints());
    }

    private void process(Order order, CreditCardInfoDTO cardInfo, Long pontosParaTrocar) {

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
        var clientId = order.getClient().getId();
        var amount = calcularValorCompra.execute(clientId, order.getTotal(), pontosParaTrocar);
        payment.setAmount(amount);
        payment.confirmPayment();
        paymentRepository.save(payment);

        // Adiciona pontos ao cliente
        pointService.addPoints(order.getClient().getId(), order.getId());

    }

    private boolean simularAutorizacao(CreditCardInfoDTO card) {
        // Simulação simples: se CVV termina com par, autoriza
        return Integer.parseInt(card.getCvv()) % 2 == 0;
    }

}