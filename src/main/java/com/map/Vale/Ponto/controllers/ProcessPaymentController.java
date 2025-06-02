package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.payments.PaymentRequestDTO;
import com.map.Vale.Ponto.services.ProcessPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/process_payment")
@Tag(name = "Process Payments", description = "Operações relacionadas a process payments")
public class ProcessPaymentController {

    private final ProcessPaymentService processPaymentService;

    public ProcessPaymentController(ProcessPaymentService processPaymentService){
        this.processPaymentService = processPaymentService;
    }
    @PostMapping
    @Operation(summary = "Iniciar processo do Payment", description = "Cria um novo Payment com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Product list cannot be empty"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID format: {product_id}"),
            @ApiResponse(responseCode = "400", description = "Invalid quantity for product ID: {quantity}"),
            @ApiResponse(responseCode = "400", description = "Client ID cannot be null"),
            @ApiResponse(responseCode = "404", description = "Client com id {clientId} não foi encontrado."),
            @ApiResponse(responseCode = "404", description = "CEP {cep} não encontrado"),
            @ApiResponse(responseCode = "422", description = "Pontos para troca: {pontosParaTrocar} pontos, pontos cliente: {clientPoints}"),
            @ApiResponse(responseCode = "400", description = "Número do cartão inválido"),
            @ApiResponse(responseCode = "422", description = "Transação recusada pela operadora")
    })
    public ResponseEntity<Void> payment(@RequestBody PaymentRequestDTO dto) {
        processPaymentService.processOrder(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
