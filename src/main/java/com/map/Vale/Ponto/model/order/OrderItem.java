package com.map.Vale.Ponto.model.order;

import com.map.Vale.Ponto.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderItem(Product product, Integer value) {

        this.product = product;
        this.quantity = value;

    }

    public BigDecimal getSubtotal() {
        return BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }

}