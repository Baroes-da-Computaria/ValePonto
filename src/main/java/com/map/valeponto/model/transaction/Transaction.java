package com.map.valeponto.model.transaction;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;
    private String description;
    private Double value;
    private String date;
    private Long quantity;
    private Double pointsGenerated;
    private Double pointsUsed;

}
