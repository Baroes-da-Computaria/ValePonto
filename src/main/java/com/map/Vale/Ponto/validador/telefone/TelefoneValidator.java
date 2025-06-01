package com.map.Vale.Ponto.validador.telefone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

    // Regex para telefones brasileiros (fixos e celulares) com DDD
    private static final Pattern PADRAO_TELEFONE = Pattern.compile(
            "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$"
    );

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        if (telefone == null || telefone.isBlank()) {
            return true; // Considera válido se for opcional (use @NotBlank junto se necessário)
        }

        String telefoneLimpo = telefone.replaceAll("\\s", "");
        return PADRAO_TELEFONE.matcher(telefoneLimpo).matches();
    }
}
