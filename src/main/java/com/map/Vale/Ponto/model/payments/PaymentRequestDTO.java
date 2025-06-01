package com.map.Vale.Ponto.model.payments;

import com.map.Vale.Ponto.enums.PaymentMethods;
import com.map.Vale.Ponto.model.address.AddressForOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

    @NotNull(message = "O campo client_id é obrigatório")
    private Long client_id;

    @NotNull(message = "O campo productIdToQuantity é obrigatório")
    private Map<String, Integer> productIdToQuantity = new HashMap<>(); // productID e Quantidade

    @Valid
    private AddressForOrder address;

    @NotNull(message = "O campo paymentMethods é obrigatório")
    private PaymentMethods paymentMethods;

    @Valid
    private CreditCardInfoDTO cardInfo;
}
