package com.map.Vale.Ponto.validador.cep;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ViaCepValidator {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isValid(String cep) {
        String cleanedCep = cep.replaceAll("\\D", "");
        String url = UriComponentsBuilder
                .fromHttpUrl("https://viacep.com.br/ws/" + cleanedCep + "/json/")
                .toUriString();
        try {
            String response = restTemplate.getForObject(url, String.class);
            return response != null && !response.contains("\"erro\": true");
        } catch (Exception e) {
            return false;
        }
    }
}

