package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.address.AddressDTO;
import com.map.Vale.Ponto.model.client.ClientRequestDTO;
import com.map.Vale.Ponto.model.client.ClientResponseDTO;
import com.map.Vale.Ponto.model.client.ClientWithAddressDTO;
import com.map.Vale.Ponto.model.points.Points;
import com.map.Vale.Ponto.services.ClientService;
import com.map.Vale.Ponto.services.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/client")
@Tag(name = "Clients", description = "Operações relacionadas a clientes")
public class ClientController {

    private final ClientService clientService;
    private final PointService pointService;

    public ClientController(ClientService clientService, PointService pointService) {
        this.clientService = clientService;
        this.pointService = pointService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar Client por id", description = "Retorna o Client com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Client encontrado"),
    })
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable("id") Long id) {
        var response = clientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{id}/details")
    @Operation(summary = "Buscar detalhes de um client", description = "Retorna os detalhes de um client com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Detalhes do Client encontrado"),
    })
    public ResponseEntity<ClientWithAddressDTO> getDetails(@PathVariable Long id) {
        var response = clientService.getDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clients", description = "Retorna uma lista paginada de todos os clients.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clients retornada com sucesso")
    })
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
    @Operation(summary = "Criar um novo client", description = "Cria um novo client com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Já existe um cliente cadastrado com esse telefone: {telefone}"),
            @ApiResponse(responseCode = "409", description = "Já existe um cliente cadastrado com esse CPF: {cpf}"),
            @ApiResponse(responseCode = "409", description = "Já existe um cliente cadastrado com esse email: {email}")
    })
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientRequestDTO dto) {
        var response = clientService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar as informações de um client existente", description = "Atualiza as informações de um client existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {id}"),
            @ApiResponse(responseCode = "409", description = "Já existe um cliente cadastrado com esse telefone: {telefone}"),
            @ApiResponse(responseCode = "409", description = "Já existe um cliente cadastrado com esse CPF: {cpf}"),
            @ApiResponse(responseCode = "409", description = "Já existe um cliente cadastrado com esse email: {email}")
    })
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO dto) {
        var response = clientService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir um client existente", description = "Exclui um client com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {id}"),
            @ApiResponse(responseCode = "400", description = "Falha de integridade referencial"),
            @ApiResponse(responseCode = "204", description = "Client excluído com sucesso")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}/address")
    @Operation(summary = "Atualizar endereço do client", description = "Atualiza o endereço de um client existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {id}"),
            @ApiResponse(responseCode = "404", description = "CEP {cep } não encontrado"),
            @ApiResponse(responseCode = "200", description = "Endereco do client atualizado com sucesso")
    })
    public ResponseEntity<Void> atualizarEndereco(@PathVariable Long id, @RequestBody AddressDTO address) {
        clientService.atualizarEndereco(id, address);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/points")
    @Operation(summary = "Listar os Points pelo ClientId", description = "Retorna os Points pelo ClientId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Points encontrados")
    })
    public ResponseEntity<Page<Points>> getPointsByClientId(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        var response = pointService.findAllByClientId(id, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
