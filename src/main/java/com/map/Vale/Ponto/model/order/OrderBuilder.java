package com.map.Vale.Ponto.model.order;

import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.client.Client;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderBuilder {

    private Client client;
    private List<OrderItem> items = new ArrayList<>();
    private Address shippingAddress;
    private BigDecimal total;

    @Column(updatable = false, name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public OrderBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    public OrderBuilder addItem(OrderItem item) {
        this.items.add(item);
        return this;
    }

    public OrderBuilder withShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public Order build() {
        Order order = new Order();

        // Set the properties of the order
        order.setClient(client);
        client.getOrders().add(order);

        // Set the items and their relationship with the order
        order.setItems(items);
        items.forEach(item -> {
            item.setOrder(order);
        });

        // Set the shipping address and its relationship with the order
        order.setShippingAddress(shippingAddress);
        shippingAddress.setOrder(order);

        // Calculate the total price of the order
        order.calculateTotal();
        return order;
    }
}
