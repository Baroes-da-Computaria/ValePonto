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
    // em alguns projetos usa o tipo do id com UUID
    // todo: de alguma forma encaixar a ideia de subtitulo
    private String name;
    private String category;
    private String description;
    private Double price;
    private String imageURL;
    private String subtitle;
    private Integer points;

    private Integer calculatePoints(){
        return (int) (price / 10);
    }
}
