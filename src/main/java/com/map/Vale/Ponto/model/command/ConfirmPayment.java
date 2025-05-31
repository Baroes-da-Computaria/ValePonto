package com.map.Vale.Ponto.model.command;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.enums.PaymentStatus;
import com.map.Vale.Ponto.model.payments.Payment;
import com.map.Vale.Ponto.repositories.PaymentRepository;
import org.springframework.stereotype.Component;


@Component
public class ConfirmPayment implements Command {

    private Long orderId;
    private final PaymentRepository paymentRepository;

    public ConfirmPayment(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public void execute(){
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado"));

        payment.setStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }

    public void execute(Long orderId) {
        this.orderId = orderId;
        this.execute();
    }
}