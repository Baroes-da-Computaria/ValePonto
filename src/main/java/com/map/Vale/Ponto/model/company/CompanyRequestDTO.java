package com.map.Vale.Ponto.model.company;

import com.map.Vale.Ponto.model.address.AddressDTO;
import com.map.Vale.Ponto.validador.cnpj.CNPJ;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDTO {

    @NotBlank(message = "O campo name é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[\\p{L}0-9 .'-]+$", message = "Caracteres inválidos no name")
    private String name;

    @NotBlank(message = "{O campo CNPJ é obrigatório}")
    @CNPJ(message = "{CNPJ inválido}")
    private String cnpj;

    // todo: substituir por um dto de endereço (evitar dependência circular)
    @NotNull(message = "{O endereço é obrigatório}")
    @Valid
    private AddressDTO address;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "Email inválido")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Formato de email inválido")
    private String email;

    public CompanyRequestDTO(Company dto){
        this.name = dto.getName();
        this.cnpj = dto.getCnpj();
        this.address = new AddressDTO(dto.getAddress());
        this.email = dto.getEmail();
    }
    // Substitua o construtor por um factory method
    public static CompanyRequestDTO fromEntity(Company entity) {
        return new CompanyRequestDTO(
                entity.getName(),
                entity.getCnpj(),
                new AddressDTO(entity.getAddress()),
                entity.getEmail()
        );
    }
}
