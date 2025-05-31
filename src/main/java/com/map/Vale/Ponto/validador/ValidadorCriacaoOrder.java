package com.map.Vale.Ponto.validador;

import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.repositories.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidadorCriacaoOrder {

    private final ClientRepository clientRepository;

    public ValidadorCriacaoOrder(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void validar(Long clientId, Map<String, Integer> productIdToQuantity) {

        validarExistenciaClient(clientId);
        validarOrdemItem(productIdToQuantity);

    }

    private void validarOrdemItem(Map<String, Integer> productIdToQuantity) {

        if (productIdToQuantity == null || productIdToQuantity.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be empty");
        }
        for (Map.Entry<String, Integer> entry : productIdToQuantity.entrySet()) {
            try {
                Long.parseLong(entry.getKey());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid product ID format: " + entry.getKey());
            }
            if (entry.getValue() == null || entry.getValue() <= 0) {
                throw new IllegalArgumentException("Invalid quantity for product ID: " + entry.getKey());
            }
        }

    }

    private void validarExistenciaClient(Long clientId) {

        if (clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException(
                    "Client com id " + clientId + " não foi encontrado.");
        }

    }

}
