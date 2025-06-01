package com.map.Vale.Ponto.model.address;

import com.map.Vale.Ponto.validador.cep.CEP;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotBlank(message = "CEP é obrigatório")
    @CEP(message = "CEP inválido")
    private String cep;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter exatamente 2 caracteres")
    @Pattern(regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO",
            message = "Estado inválido. Use a sigla UF (ex: PB,PE e RN)")
    private String state;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 2, max = 100, message = "Cidade deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Cidade contém caracteres inválidos")
    private String city;

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 2, max = 200, message = "Logradouro deve ter entre {min} e {max} caracteres")
    private String road;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 20, message = "Número deve ter até {max} caracteres")
    @Pattern(regexp = "^[\\p{L}0-9 .#-|s/n]+$", message = "Número contém caracteres inválidos")
    private String number;

    public AddressDTO(Address address) {
        this.cep = address.getCep();
        this.state = address.getState();
        this.city = address.getCity();
        this.road = address.getRoad();
        this.number = address.getNumber();
    }
}