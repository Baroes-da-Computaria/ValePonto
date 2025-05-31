package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.model.address.AddressForOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long client_id;
    Map<String, Integer> productIdToQuantity = new HashMap<>(); // productID e Quantidade
    AddressForOrder addressForOrder;
    private PaymentMethods paymentMethods;
    private CreditCardInfoDTO cardInfo;
}
