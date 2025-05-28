package com.map.Vale.Ponto.model.client;

import com.map.Vale.Ponto.model.address.AddressForClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientWithAddressDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String password;
    private String telefone;
    private AddressForClient address;

    public ClientWithAddressDTO(Client entity) {
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.password = entity.getPassword();
        this.telefone = entity.getTelefone();
        if (entity.getAddress() != null) {
            this.address = new AddressForClient(entity.getAddress());
        }
    }
}
