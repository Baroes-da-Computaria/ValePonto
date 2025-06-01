package com.map.Vale.Ponto.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "A categoria é obrigatória")
    @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres")
    private String category;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String description;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser positivo")
    private Double price;

    @NotBlank(message = "A URL da imagem é obrigatória")
    @Size(max = 255, message = "A URL da imagem deve ter no máximo 255 caracteres")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "A URL da imagem deve ser válida (começar com http:// ou https://)"
    )
    private String imageURL;

    @NotBlank(message = "O subtítulo é obrigatório")
    @Size(max = 150, message = "O subtítulo deve ter no máximo 150 caracteres")
    private String subtitle;

    @NotNull(message = "O ID da empresa é obrigatório")
    @Positive(message = "O ID da empresa deve ser um número positivo")
    private Long companyId;
}

