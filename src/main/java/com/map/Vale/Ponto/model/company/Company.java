package com.map.Vale.Ponto.model.company;

import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
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

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public Company(CompanyRequestDTO dto) {
        this.name = dto.getName();
        this.cnpj = dto.getCnpj();
        this.address = dto.getAddress();
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
        if(dto.getAddress() != null) {
            dto.getAddress().update(dto.getAddress());
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }

    }
}
