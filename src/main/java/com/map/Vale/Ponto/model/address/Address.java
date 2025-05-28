package com.map.Vale.Ponto.model.address;

import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @Column(updatable = false, name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public void update(AddressForClient entity) {

        if (entity.getCep() != null) {
            this.cep = entity.getCep();
        }
        if (entity.getState() != null) {
            this.state = entity.getState();
        }
        if (entity.getCity() != null) {
            this.city = entity.getCity();
        }
        if (entity.getRoad() != null) {
            this.road = entity.getRoad();
        }
        if (entity.getNumber() != null) {
            this.number = entity.getNumber();
        }

    }

}