package com.map.Vale.Ponto.model.address;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressForClient {
    private String cep;
    private String state;
    private String city;
    private String road;
    private String number;

    public AddressForClient(Address entity){
        this.cep = entity.getCep();
        this.state = entity.getState();
        this.city = entity.getCity();
        this.road = entity.getRoad();
        this.number = entity.getNumber();
    }
}
