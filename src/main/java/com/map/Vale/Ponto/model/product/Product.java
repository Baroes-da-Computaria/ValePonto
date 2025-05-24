package com.map.Vale.Ponto.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    @Column(name = "subtitle", nullable = false)
    private String subtitle;

    @Column(name = "points", nullable = false)
    private Integer points;

    public Integer calculatePoints() {
        return (int)(this.price * 0.1);
    }

    public Product(ProductRequestDTO dto) {
        this.name = dto.getName();
        this.category = dto.getCategory();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.imageURL = dto.getImageURL();
        this.subtitle = dto.getSubtitle();
        this.points = calculatePoints();
    }

    public void updateFromRequest(ProductRequestDTO dto) {
        this.setName(dto.getName());
        this.setCategory(dto.getCategory());
        this.setDescription(dto.getDescription());
        this.setPrice(dto.getPrice());
        this.setSubtitle(dto.getSubtitle());
        this.setImageURL(dto.getImageURL());
        this.setPoints(calculatePoints());
    }
}
