package com.map.Vale.Ponto.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long product_id;
    private Integer quantity;

    public OrderItemDTO(OrderItem orderItem) {

        this.product_id = orderItem.getProduct().getId();
        this.quantity = orderItem.getQuantity();

    }
}
