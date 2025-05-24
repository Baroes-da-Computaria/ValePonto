package com.map.Vale.Ponto.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String password;
    private String telefone;
}
