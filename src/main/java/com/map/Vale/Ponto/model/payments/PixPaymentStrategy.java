package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.enums.PaymentStatus;
import com.map.Vale.Ponto.model.command.ConfirmPayment;
import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.repositories.OrderRepository;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import com.map.Vale.Ponto.services.OrderService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component("PIX")
public class PixPaymentStrategy implements PaymentStrategy {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final ConfirmPayment confirmPayment;
    private final CalcularValorCompra calcularValorCompra;

    public PixPaymentStrategy(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            OrderService orderService,
            ConfirmPayment confirmPayment,
            CalcularValorCompra calcularValorCompra
    ) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.confirmPayment = confirmPayment;
        this.calcularValorCompra = calcularValorCompra;
    }

    @Override
    public void processPayment(PaymentRequestDTO dto){
        var order = orderService.createBuilder(dto.getClient_id(), dto.getAddress(), dto.getProductIdToQuantity());
        process(order,dto.getPoints());
    }

    private void process(Order order,Long pontosParaTrocar) {
        String pixCode = gerarPixCode(order.getId(), order.getTotal());
        Payment payment = new Payment();
        payment.setOrder(orderRepository.findById(order.getId()).get());
        payment.setMethod(PaymentMethods.PIX);
        payment.setStatus(PaymentStatus.PENDING);
        var clientId = order.getClient().getId();
        var amount = calcularValorCompra.execute(clientId, order.getTotal(), pontosParaTrocar);
        payment.setAmount(amount);
        paymentRepository.save(payment);
    }

    public void confirmPayment(Long orderId){
        confirmPayment.execute(orderId);
    }

    private String gerarPixCode(Long orderId, BigDecimal amount) {
        // Exemplo simples de QR code (poderia usar lib como gerencianet, pix4j, etc)
        return "00020126360014BR.GOV.BCB.PIX0114+5531999999995204000053039865404"
                + amount.setScale(2)
                + "5802BR5913Loja Exemplo6009SAO PAULO62070503***6304"
                + UUID.randomUUID().toString().substring(0, 4);
    }

}
