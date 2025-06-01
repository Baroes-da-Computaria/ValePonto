package com.map.Vale.Ponto.validador.cnpj;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CNPJValidator implements ConstraintValidator<CNPJ, String> {

    private static final Pattern CNPJ_PATTERN = Pattern.compile(
            "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}"
    );

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null || cnpj.isBlank()) return false;

        String cleaned = cnpj.replaceAll("[^\\d]", "");

        if (cleaned.length() != 14 || !CNPJ_PATTERN.matcher(cnpj).matches()) {
            return false;
        }
        return isValidCNPJ(cleaned);
    }

    private boolean isValidCNPJ(String cnpj) {
        // Verifica se todos os dígitos são iguais (ex: 00000000000000)
        if (cnpj.chars().distinct().count() == 1) return false;

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        String base = cnpj.substring(0, 12);

        int digito1 = calcularDigito(base, pesos1);
        int digito2 = calcularDigito(base + digito1, pesos2);

        String calculado = base + digito1 + digito2;
        return cnpj.equals(calculado);
    }

    private int calcularDigito(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            soma += Character.getNumericValue(str.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
