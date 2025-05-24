package com.map.Vale.Ponto.model.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "address")
    private String address;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String city;

    @Column(name = "city")
    private String state;

    @Column(name = "email")
    private String email;
}
