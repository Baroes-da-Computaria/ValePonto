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

    public Company(CompanyRequestDTO dto) {
        this.name = dto.getName();
        this.cnpj = dto.getCnpj();
        this.address = dto.getAddress();
        this.number = dto.getNumber();
        this.city = dto.getCity();
        this.state = dto.getState();
        this.email = dto.getEmail();
    }

    public void updateFromRequest(CompanyRequestDTO dto) {
        if (dto.getName() != null) {
            this.name = dto.getName();
        }
        if (dto.getCnpj() != null) {
            this.cnpj = dto.getCnpj();
        }
        if (dto.getAddress() != null) {
            this.address = dto.getAddress();
        }
        if (dto.getNumber() != null) {
            this.number = dto.getNumber();
        }
        if (dto.getCity() != null) {
            this.city = dto.getCity();
        }
        if (dto.getState() != null) {
            this.state = dto.getState();
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
    }
}
