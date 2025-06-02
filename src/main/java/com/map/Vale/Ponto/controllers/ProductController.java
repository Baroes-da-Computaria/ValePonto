package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.product.ProductDetailsDTO;
import com.map.Vale.Ponto.model.product.ProductRequestDTO;
import com.map.Vale.Ponto.model.product.ProductResponseDTO;
import com.map.Vale.Ponto.model.product.ProductSalesView;
import com.map.Vale.Ponto.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/product")
@Tag(name = "Products", description = "Operações relacionadas a products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productServices) {
        this.productService = productServices;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar Product por id", description = "Retorna o Product com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product não encontrado com id: {id}"),
            @ApiResponse(responseCode = "200", description = "Detalhes do Product encontrados"),
    })
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable("id") Long id) {
        var response = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os Products", description = "Retorna uma lista paginada de todos os Products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Products retornada com sucesso")
    })
    public ResponseEntity<Page<ProductResponseDTO>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {

        // instancia do pageable para a busca paginada
        var pageable = PageRequest.of(pageNumber, pageSize);

        // chama o service para a busca paginada
        var page = productService.findAll(pageable);

        // retorna a pagina de product response
        return ResponseEntity.status(HttpStatus.OK).body(page);

    }

    @PostMapping
    @Operation(summary = "Criar um novo Product", description = "Cria um novo Product com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Company com id {companyId} não encontrada."),
            @ApiResponse(responseCode = "409",description = "Product {productName} já existe na company informada.")

    })
    public ResponseEntity<ProductDetailsDTO> create(@Valid @RequestBody ProductRequestDTO dto) {
        var response = productService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{id}/{id_company}")
    @Operation(summary = "Atualizar informações de um Product existente", description = "Atualiza as informações de um Product existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Company não encontrado com id: {id}"),
            @ApiResponse(responseCode = "409",description = "Product {productName} não existe na company informada."),
            @ApiResponse(responseCode = "404", description = "Product não encontrado com id: {id}")
    })
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id,@PathVariable Long id_company,@Valid @RequestBody ProductRequestDTO dto) {
        var response = productService.update(id, id_company, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir um Product existente", description = "Exclui um Product com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product não encontrado com id: {id}"),
            @ApiResponse(responseCode = "400", description = "Falha de integridade referencial"),
            @ApiResponse(responseCode = "204", description = "Product excluído com sucesso")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/top-sold")
    @Operation(summary = "Listar os Product mais vendidos", description = "Retorna os Product mais vendidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "O preço mínimo não pode ser negativo"),
            @ApiResponse(responseCode = "400", description = "O preço máximo não pode ser negativo"),
            @ApiResponse(responseCode = "400", description = "O preço mínimo não pode ser maior que o preço máximo")
    })
    public ResponseEntity<Page<ProductSalesView>> getTopSoldProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false, defaultValue = "totalSold") String sort,
            @RequestParam(required = false, defaultValue = "DESC") String direction,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Sort.Direction sortDirection = Sort.Direction.fromOptionalString(direction.toUpperCase())
                .orElse(Sort.Direction.DESC);

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(sortDirection, sort)
        );

        var topProducts = productService.getTopSoldProducts(category, minPrice, maxPrice, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(topProducts);
    }
}
