package com.map.Vale.Ponto.model.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String cep;
    private String state;
    private String city;
    private String road;
    private String number;

    public AddressDTO(Address address) {
        this.cep = address.getCep();
        this.state = address.getState();
        this.city = address.getCity();
        this.road = address.getRoad();
        this.number = address.getNumber();
    }
}
