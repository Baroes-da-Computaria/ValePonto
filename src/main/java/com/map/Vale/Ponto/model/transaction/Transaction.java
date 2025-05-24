package com.map.Vale.Ponto.model.transaction;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private Double value;

    @Column(name = "date")
    private String date;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "points_generated")
    private Double pointsGenerated;

    @Column(name = "points_used")
    private Double pointsUsed;

}
