package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.client.ClientRequestDTO;
import com.map.Vale.Ponto.model.client.ClientResponseDTO;
import com.map.Vale.Ponto.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> geById(@PathVariable("id") Long id) {

        var response = clientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {

        // instancia do pageable para a busca paginada
        var pageable = PageRequest.of(pageNumber, pageSize);

        // chama o service para a busca paginada
        var page = clientService.findAll(pageable);

        // retorna a pagina de product response
        return ResponseEntity.status(HttpStatus.OK).body(page);

    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody ClientRequestDTO dto) {

        var response = clientService.save(dto);

        // retorna o product response
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(
            value = "/{id}"
    )
    public ResponseEntity<ClientResponseDTO> update(@PathVariable("id") Long id, @RequestBody ClientRequestDTO dto) {

        var response = clientService.update(id, dto);
        // retorna o curso response
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        // chama o service para deletar o client
        clientService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
