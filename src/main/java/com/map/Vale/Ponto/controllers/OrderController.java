package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.order.OrderRequestDTO;
import com.map.Vale.Ponto.model.order.OrderResponseDTO;
import com.map.Vale.Ponto.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/orders")
@Tag(name = "Orders", description = "Operações relacionadas a orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar Order por id", description = "Retorna o Order com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Client não encontrado com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Detalhes do Order encontrados"),
    })
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable("id") Long id) {

        var response = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @GetMapping
    @Operation(summary = "Listar todos os Orders", description = "Retorna uma lista paginada de todos os Orders.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Orders retornada com sucesso")
    })
    public ResponseEntity<Page<OrderResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {

        // instancia do pageable para a busca paginada
        var pageable = PageRequest.of(pageNumber, pageSize);

        // chama o service para a busca paginada
        var page = orderService.findAll(pageable);

        // retorna a pagina de product response
        return ResponseEntity.status(HttpStatus.OK).body(page);

    }

    @PostMapping
    @Operation(summary = "Criar um novo Orders", description = "Cria um novo Orders com base nos dados fornecidos.")
    @ApiResponses(value = {
            //@ApiResponse(responseCode = "409",description = "Product com esse nome já existe")

    })
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO dto) {

        var order = orderService.createBuilder(dto.getClientId(),dto.getClientForOrder(), dto.getProducts());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }


    @Operation(summary = "Excluir um Order existente", description = "Exclui um Order com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Order não encontrado com id: {id}"),
            @ApiResponse(responseCode = "400", description = "Falha de integridade referencial"),
            @ApiResponse(responseCode = "204", description = "Orders excluído com sucesso")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        // chama o service para deletar o product
        orderService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
