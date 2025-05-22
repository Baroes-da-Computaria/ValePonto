package com.map.Vale.Ponto.model.product;

import com.map.Vale.Ponto.enums.ProductCategories;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
    private String name;
    private ProductCategories category;
    private String description;
    private String subtitle;
    private Double price;
    private String imageURL;
}
