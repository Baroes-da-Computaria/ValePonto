package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.company.CompanyRequestDTO;
import com.map.Vale.Ponto.model.company.CompanyResponseDTO;
import com.map.Vale.Ponto.services.CompanyService;
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
@RequestMapping("/valeponto/company")
@Tag(name = "Companies", description = "Operações relacionadas a company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar Company por id", description = "Retorna a Company com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Company não encontrada com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Detalhes da Company encontrados"),
    })
    public ResponseEntity<CompanyResponseDTO> geById(@PathVariable("id") Long id) {
        var response = companyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as Company", description = "Retorna uma lista paginada de todas as Company.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Company retornada com sucesso")
    })
    public ResponseEntity<Page<CompanyResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {

        // instancia do pageable para a busca paginada
        var pageable = PageRequest.of(pageNumber, pageSize);

        // chama o service para a busca paginada
        var page = companyService.findAll(pageable);

        // retorna a pagina de product response
        return ResponseEntity.status(HttpStatus.OK).body(page);

    }

    @PostMapping
    @Operation(summary = "Criar uma nova Company", description = "Cria um nova Company com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Company com esse {nome} já existe"),
            @ApiResponse(responseCode = "409", description = "Company com esse {cnpj} já existe"),
            @ApiResponse(responseCode = "404", description = "CEP {cep} não encontrado")

    })
    public ResponseEntity<CompanyResponseDTO> create(@Valid @RequestBody CompanyRequestDTO dto) {
        var response = companyService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar informações de uma Company existente", description = "Atualiza as informações de um Company existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Company não encontrada com id: {id}"),
            @ApiResponse(responseCode = "409", description = "Company com esse {nome} já existe"),
            @ApiResponse(responseCode = "409", description = "Company com esse {cnpj} já existe"),
            @ApiResponse(responseCode = "404", description = "CEP {cep} não encontrado")
    })
    public ResponseEntity<CompanyResponseDTO> update(@PathVariable("id") Long id,@Valid @RequestBody CompanyRequestDTO dto) {
        var response = companyService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir uma Company existente", description = "Exclui Company com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Company não encontrado com id: {id}"),
            @ApiResponse(responseCode = "400", description = "Falha de integridade referencial"),
            @ApiResponse(responseCode = "204", description = "Company excluída com sucesso")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        companyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
