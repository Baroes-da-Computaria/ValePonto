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
    // todo: colocar uma pontuacao conforme a logica que definimos de a cada 10 reais um ponto
    // todo(opcional): colocar a chave estrangeira para a empresa para ter acesso a imagem da empresa conforme o figma
    private String name;

    // todo: uma classe ou enum
    private String category;
    private String description;
    private Double price;
    private String imageURL;
}
