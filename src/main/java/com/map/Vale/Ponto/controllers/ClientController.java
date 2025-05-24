package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.client.Client;
import com.map.Vale.Ponto.model.client.ClientRequestDTO;
import com.map.Vale.Ponto.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientRequestDTO dto) {

        // chama o service para salvar o curso
        var client = clientService.create(dto);

        return ResponseEntity.ok(client);
    }
}
