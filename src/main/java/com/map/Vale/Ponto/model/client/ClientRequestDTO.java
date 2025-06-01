package com.map.Vale.Ponto.model.client;

import com.map.Vale.Ponto.model.address.AddressDTO;
import com.map.Vale.Ponto.validador.telefone.Telefone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    @NotBlank(message = "O campo firstName é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Caracteres inválidos no nome")
    private String firstName;

    @NotBlank(message = "O campo lastName é obrigatório")
    @Size(min = 2, max = 100, message = "O sobrenome deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Caracteres inválidos no sobrenome")
    private String lastName;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "Email inválido")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "O campo cpf é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "O campo password é obrigatório")
    @Size(min = 8, max = 20, message = "Senha deve ter 8-20 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Senha deve conter: 1 maiúscula, 1 minúscula, 1 número, 1 especial"
    )
    private String password;

    @Telefone(message = "Telefone inválido")
    private String telefone;

    @Valid
    private AddressDTO address;
}