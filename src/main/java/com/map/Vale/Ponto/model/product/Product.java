package com.map.Vale.Ponto.model.product;

import com.map.Vale.Ponto.model.company.Company;
import com.map.Vale.Ponto.model.order.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    //@Enumerated(EnumType.STRING)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Product(ProductRequestDTO dto) {
        this.name = dto.getName();
        this.category = dto.getCategory();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.imageURL = dto.getImageURL();
        this.subtitle = dto.getSubtitle();
        this.points = calculatePoints();
    }

    public Integer calculatePoints() {
        return (int) (this.price * 0.1);
    }

    public void updateFromRequest(ProductRequestDTO dto) {

        if (dto.getName() != null) {
            this.setName(dto.getName());
        }
        if (dto.getCategory() != null) {
            this.setCategory(dto.getCategory());
        }
        if (dto.getDescription() != null) {
            this.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            this.setPrice(dto.getPrice());
            this.setPoints(calculatePoints());
        }
        if (dto.getImageURL() != null) {
            this.setImageURL(dto.getImageURL());
        }
        if (dto.getSubtitle() != null) {
            this.setSubtitle(dto.getSubtitle());
        }

    }
}
