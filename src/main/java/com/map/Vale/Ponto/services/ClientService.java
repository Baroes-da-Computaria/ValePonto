package com.map.Vale.Ponto.services;

import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.client.ClientRequestDTO;
import com.map.Vale.Ponto.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(ClientRequestDTO dto) {
        Client client = new Client(dto);
        return clientRepository.save(client);
    }
}
