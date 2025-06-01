package com.map.Vale.Ponto.model.company;

import com.map.Vale.Ponto.model.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailsDTO {
    private String name;
    private String cnpj;
    private Address address;
    private String email;

    public CompanyDetailsDTO(Company entity){
        this.name = entity.getName();
        this.cnpj = entity.getCnpj();
        this.address = entity.getAddress();
        this.email = entity.getEmail();
    }
}
