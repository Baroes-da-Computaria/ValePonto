package com.map.Vale.Ponto.model.address;

import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.company.Company;
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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "road")
    private String road;

    @Column(name = "number")
    private String number;

    @OneToOne(mappedBy = "address")
    private Company company;

    @OneToOne(mappedBy = "address")
    private Client client;

    public void update(Address entity) {

        if (entity.getCep() != null) {
            this.cep = entity.cep;
        }
        if (entity.getState() != null) {
            this.state = entity.state;
        }
        if (entity.getCity() != null) {
            this.city = entity.city;
        }
        if (entity.getRoad() != null) {
            this.road = entity.road;
        }
        if (entity.getNumber() != null) {
            this.number = entity.number;
        }

    }

}