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
@RequestMapping("/valeponto/payments")
@Tag(name = "Payments", description = "Operações relacionadas a payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar Payment por id", description = "Retorna o Payment com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Payment não encontrado com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Detalhes do Payment encontrados")
    })
    public ResponseEntity<PaymentDetailsDTO> getById(@PathVariable Long id) {
        var response = paymentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{id}/confirm")
    @Operation(summary = "Atualizar o status de um Payment existente", description = "Atualiza as informações de um Payment existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Payment não encontrado com id: {id}"),
            @ApiResponse(responseCode = "422", description = "Pagamento já confirmado ou cancelado"),
            @ApiResponse(responseCode = "422", description = "Pedido associado ao pagamento não encontrado"),
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {client_id}"),
            @ApiResponse(responseCode = "404", description = "Order não encontrado com id: {order_id}"),
            @ApiResponse(responseCode = "404", description = "Client with id: {client_id} does not match the order's client id: {order_client_id}")
    })
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id) {
        paymentService.confirmPayment(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
