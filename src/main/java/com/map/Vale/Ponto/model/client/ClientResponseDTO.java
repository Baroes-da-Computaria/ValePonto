package com.map.Vale.Ponto.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {

    private String firstName;
    private String lastName;
    private String email;

    public ClientResponseDTO(Client client) {

        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();

    }

}
