package com.map.Vale.Ponto.model.command;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.enums.PaymentStatus;
import com.map.Vale.Ponto.model.payments.Payment;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import com.map.Vale.Ponto.services.PointService;
import org.springframework.stereotype.Component;


@Component
public class ConfirmPayment implements Command {

    private Long orderId;
    private final PaymentRepository paymentRepository;
    private final PointService pointService;

    public ConfirmPayment(
            PaymentRepository paymentRepository,
            PointService pointService
    ) {
        this.paymentRepository = paymentRepository;
        this.pointService = pointService;
    }


    @Override
    public void execute(){
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado"));

        payment.confirmPayment();
        paymentRepository.save(payment);
        // adiciona pontos ao cliente
        pointService.addPoints(payment.getOrder().getClient().getId(), payment.getOrder().getId());

    }

    public void execute(Long orderId) {
        this.orderId = orderId;
        this.execute();
    }
}