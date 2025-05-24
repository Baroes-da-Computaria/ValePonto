package com.map.Vale.Ponto.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private String name;
    private String category;
    private String description;
    private String subtitle;
    private Double price;
    private String imageURL;
}
