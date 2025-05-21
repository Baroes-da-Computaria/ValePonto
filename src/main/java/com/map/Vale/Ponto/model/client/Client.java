package com.map.Vale.Ponto.model.client;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String password;
    private String telefone;


}
