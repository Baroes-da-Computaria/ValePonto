package com.map.Vale.Ponto.model.client;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
}
