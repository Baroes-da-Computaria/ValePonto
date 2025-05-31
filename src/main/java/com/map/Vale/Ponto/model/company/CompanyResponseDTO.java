package com.map.Vale.Ponto.model.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDTO {

    private String name;
    private String cnpj;
    private String email;

    public CompanyResponseDTO(Company company) {

        this.name = company.getName();
        this.cnpj = company.getCnpj();
        this.email = company.getEmail();

    }
}
