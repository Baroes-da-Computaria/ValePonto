package com.map.Vale.Ponto.model.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDTO {
    private String name;
    private String cnpj;
    private String address;
    private String number;
    private String city;
    private String state;
    private String email;


    public CompanyRequestDTO(Company dto){
        this.name = dto.getName();
        this.cnpj = dto.getCnpj();
        this.address = dto.getAddress();
        this.number = dto.getNumber();
        this.city = dto.getCity();
        this.state = dto.getState();
        this.email = dto.getEmail();
    }
}
