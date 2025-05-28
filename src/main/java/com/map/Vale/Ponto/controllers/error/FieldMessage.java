package com.map.Vale.Ponto.controllers.error;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage {
    private String fieldName;
    private String message;
}
