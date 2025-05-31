package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.payments.PaymentRequestDTO;
import com.map.Vale.Ponto.services.ProcessPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valeponto/payment")
@Tag(name = "Payment", description = "Operações relacionadas a payments")
public class ProcessPaymentController {

    private final ProcessPaymentService processPaymentService;


    public ProcessPaymentController(ProcessPaymentService processPaymentService){
        this.processPaymentService = processPaymentService;
    }

//    @PostMapping
//    @Operation(summary = "Iniciar Payment", description = "Cria um novo Payment com base nos dados fornecidos.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "409",description = "Product com esse nome já existe")
//
//    })
////    public ResponseEntity<Void> create(@RequestBody PaymentRequestDTO dto) {
////
////        var response = processPaymentService.processOrder(dto);
////
////        // retorna o payment response
////        return ResponseEntity.status(HttpStatus.OK).body();
////    }
}
