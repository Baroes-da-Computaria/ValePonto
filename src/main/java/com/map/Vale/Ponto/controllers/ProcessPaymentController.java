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
            @ApiResponse(responseCode = "409", description = "Product com esse nome já existe")

    })
    public ResponseEntity<Void> payment(@RequestBody PaymentRequestDTO dto) {
        processPaymentService.processOrder(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
