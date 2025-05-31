package com.map.Vale.Ponto.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private String name;
    private String category;
    private Double price;
    private String imageURL;

    public ProductResponseDTO(Product product) {

        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.imageURL = product.getImageURL();

    }
}
