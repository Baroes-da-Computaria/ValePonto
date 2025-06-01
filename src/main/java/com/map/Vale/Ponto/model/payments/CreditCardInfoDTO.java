package com.map.Vale.Ponto.model.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardInfoDTO {
    private String cardNumber;
    private String holderName;
    private String expiry;
    private String cvv;
}

