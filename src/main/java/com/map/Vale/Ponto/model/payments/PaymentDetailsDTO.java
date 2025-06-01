package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.enums.PaymentStatus;
import com.map.Vale.Ponto.model.order.OrderResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsDTO {
    private OrderResponseDTO order;
    private PaymentMethods method;
    private PaymentStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String pixCode;

    public PaymentDetailsDTO(Payment payment) {
        this.order = new OrderResponseDTO(payment.getOrder());
        this.method = payment.getMethod();
        this.status = payment.getStatus();
        this.createdDate = payment.getCreatedDate();
        this.lastModifiedDate = payment.getLastModifiedDate();
        this.pixCode = payment.getPixCode();
    }
}
