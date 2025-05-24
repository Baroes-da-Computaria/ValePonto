package com.map.Vale.Ponto.model.client;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    public Client(ClientRequestDTO dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.cpf = dto.getCpf();
        this.password = dto.getPassword();
        this.telefone = dto.getTelefone();
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
