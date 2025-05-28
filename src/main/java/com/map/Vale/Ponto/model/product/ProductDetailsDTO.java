package com.map.Vale.Ponto.model.product;

import com.map.Vale.Ponto.model.company.CompanyResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDTO {
    private String name;
    private String category;
    private Double price;
    private String imageURL;
    private CompanyResponseDTO company;

    public ProductDetailsDTO(Product entity) {
        this.name = entity.getName();
        this.category = entity.getCategory();
        this.price = entity.getPrice();
        this.imageURL = entity.getImageURL();
        this.company = new CompanyResponseDTO(entity.getCompany());
    }
}
