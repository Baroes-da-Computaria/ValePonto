package com.map.Vale.Ponto.model.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.time.YearMonth;

@Getter
@ToString(exclude = {"cardNumber", "cvv"})
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true) // Para frameworks
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CreditCardInfoDTO {

    @NotBlank(message = "O campo cardNumber é obrigatório")
    @CreditCardNumber
    private String cardNumber;

    @NotBlank(message = "O campo holderName é obrigatório")
    @Size(min = 2, max = 50)
    private String holderName;

    @NotNull(message = "O campo expiry é obrigatório")
    private String expiry;

    @NotBlank(message = "O campo cvv é obrigatório")
    @Pattern(regexp = "\\d{3,4}")
    private String cvv;
}

