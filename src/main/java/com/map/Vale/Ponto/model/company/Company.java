package com.map.Vale.Ponto.model.company;

import com.map.Vale.Ponto.model.address.Address;
import com.map.Vale.Ponto.model.address.AddressForClient;
import com.map.Vale.Ponto.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @Column(updatable = false, name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

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
        if (dto.getAddress() != null) {
            this.address.update(new AddressForClient(dto.getAddress()));
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }

    }
}
