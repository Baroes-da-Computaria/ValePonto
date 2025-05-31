package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.payments.PaymentDetailsDTO;
import com.map.Vale.Ponto.model.product.ProductRequestDTO;
import com.map.Vale.Ponto.model.product.ProductResponseDTO;
import com.map.Vale.Ponto.services.PaymentService;
import com.map.Vale.Ponto.services.ProcessPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/payment")
@Tag(name = "Payment", description = "Operações relacionadas a payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Buscar Payment por id", description = "Retorna o Payment com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product não encontrado com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Detalhes do Product encontrados"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentDetailsDTO> getById(@PathVariable Long id) {

        var response = paymentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body((PaymentDetailsDTO) response);

    }

    @PutMapping(value = "/{id}/confirm")
    @Operation(summary = "Atualizar o status de um Payment existente", description = "Atualiza as informações de um Payment existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product não encontrado com id: {id}"),
    })
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id) {

        paymentService.confirmPayment(id);

        // retorna o curso response
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
