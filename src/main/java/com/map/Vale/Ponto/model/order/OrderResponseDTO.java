package com.map.Vale.Ponto.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long client_id;
    private List<OrderItemDTO> orders = new ArrayList<>();
    private BigDecimal total;

    public OrderResponseDTO(Order entity) {

        this.client_id = entity.getClient().getId();
        this.orders = entity.getItems().stream().map(OrderItemDTO::new).collect(Collectors.toList());
        this.total = entity.getTotal();

    }

}
