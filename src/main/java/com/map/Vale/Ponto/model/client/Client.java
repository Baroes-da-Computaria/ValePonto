package com.map.Vale.Ponto.model.client;

import com.map.Vale.Ponto.model.order.Order;
import com.map.Vale.Ponto.model.address.Address;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "telefone", nullable = false, unique = true)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @Column(updatable = false, name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Client(ClientRequestDTO dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.cpf = dto.getCpf();
        this.password = dto.getPassword();
        this.telefone = dto.getTelefone();
        this.address = new Address(dto.getAddress());
    }

    public void updateFromRequest(ClientRequestDTO dto) {

        if (dto.getFirstName() != null) {
            this.firstName = dto.getFirstName();
        }
        if (dto.getLastName() != null) {
            this.lastName = dto.getLastName();
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        if (dto.getCpf() != null) {
            this.cpf = dto.getCpf();
        }
        if (dto.getPassword() != null) {
            this.password = dto.getPassword();
        }
        if (dto.getTelefone() != null) {
            this.telefone = dto.getTelefone();
        }

    }
}
