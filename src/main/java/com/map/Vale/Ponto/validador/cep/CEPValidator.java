package com.map.Vale.Ponto.validador.cep;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CEPValidator implements ConstraintValidator<CEP, String> {

    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{5}-?\\d{3}$");

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || cep.isBlank()) return false;
        return CEP_PATTERN.matcher(cep).matches();
    }
}


