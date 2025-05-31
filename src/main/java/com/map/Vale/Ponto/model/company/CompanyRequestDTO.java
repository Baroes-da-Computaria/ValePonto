package com.map.Vale.Ponto.model.company;

import com.map.Vale.Ponto.model.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDTO {

    private String name;
    private String cnpj;
    private Address address;
    private String email;

    public CompanyRequestDTO(Company dto){

        this.name = dto.getName();
        this.cnpj = dto.getCnpj();
        this.address = dto.getAddress();
        this.email = dto.getEmail();

    }
}
