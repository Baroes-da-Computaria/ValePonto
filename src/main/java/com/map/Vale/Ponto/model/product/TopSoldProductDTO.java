package com.map.Vale.Ponto.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopSoldProductDTO {
    private Long id;
    private String name;
    private String category;
    private String description;
    private Double price;
    private String imageURL;
    private String subtitle;
    private Long points;
    private Long totalSold; // Quantidade total vendida

    public TopSoldProductDTO(
            Long id, String name,
            String category, String description,
            Double price, String imageURL,
            String subtitle, Long points,
            Long totalSold
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.subtitle = subtitle;
        this.points = points;
        this.totalSold = totalSold;
    }
}