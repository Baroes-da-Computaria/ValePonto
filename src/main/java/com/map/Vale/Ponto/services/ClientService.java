package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.controllers.error.DataBaseException;
import com.map.Vale.Ponto.controllers.error.ResourceNotFoundException;
import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.client.ClientRequestDTO;
import com.map.Vale.Ponto.model.client.ClientResponseDTO;
import com.map.Vale.Ponto.model.product.Product;
import com.map.Vale.Ponto.model.product.ProductResponseDTO;
import com.map.Vale.Ponto.repositories.ClientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO findById(Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + id));

        return new ClientResponseDTO(client);
    }

    public Page<ClientResponseDTO> findAll(PageRequest pageable) {
        return clientRepository.findAll(pageable).map(ClientResponseDTO::new);
    }

    public ClientResponseDTO save(ClientRequestDTO dto) {
        var client = new Client(dto);
        var saved = clientRepository.save(client);
        return new ClientResponseDTO(saved);
    }

    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + id));

        client.updateFromRequest(dto);
        var updated = clientRepository.save(client);
        return new ClientResponseDTO(updated);
    }

    public void delete(Long id) {

        // verifica se esse client existe
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com id: " + id);
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }

    }
}
