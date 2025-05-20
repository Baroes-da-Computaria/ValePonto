package com.map.valeponto.model.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    private String name;
    private String category;
    private String description;
    private Double price;
    private String imageURL;


}
