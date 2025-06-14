package com.map.Vale.Ponto.model.order;

import com.map.Vale.Ponto.model.address.AddressForOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private Long clientId;
    private Map<String, Integer> products = new HashMap<>(); // productId -> quantity
    private AddressForOrder clientForOrder;
}
