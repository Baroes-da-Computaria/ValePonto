package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.model.payments.PaymentRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class ProcessPaymentService {

    private final OrderService orderService;
    private final ClientService clientService;
    private final PaymentService paymentService;
    //private final NotificationService notificationService;

    public ProcessPaymentService(
            OrderService orderService,
            ClientService clientService,
            PaymentService paymentService
            //NotificationService notificationService

    ){
        this.orderService = orderService;
        this.clientService = clientService;
        this.paymentService = paymentService;
        //this.notificationService = notificationService;
    }

    public void processOrder(PaymentRequestDTO dto) {
        paymentService.process(dto);
        //notificationService.sendEmailConfirmation(order);
    }
}