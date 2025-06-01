package com.map.Vale.Ponto.model.command;

import com.map.Vale.Ponto.controllers.error.BusinessException;
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
        Payment payment = paymentRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado"));
        validar(payment);
        payment.confirmPayment();
        paymentRepository.save(payment);
        pointService.addPoints(payment.getOrder().getClient().getId(), payment.getOrder().getId());
    }

    private void validar(Payment payment) {
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new BusinessException("Pagamento já confirmado ou cancelado");
        }
        if (payment.getOrder() == null) {
            throw new ResourceNotFoundException("Pedido associado ao pagamento não encontrado");
        }
    }

    public void execute(Long orderId) {
        this.orderId = orderId;
        this.execute();
    }
}