package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.product.ProductDetailsDTO;
import com.map.Vale.Ponto.model.product.ProductRequestDTO;
import com.map.Vale.Ponto.model.product.ProductResponseDTO;
import com.map.Vale.Ponto.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valeponto/product")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productServices) {
        this.productService = productServices;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable("id") Long id) {

        var response = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping
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
    public ResponseEntity<ProductDetailsDTO> create(@RequestBody ProductRequestDTO dto) {

        var response = productService.save(dto);

        // retorna o product response
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(
            value = "/{id}"
    )
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id, @RequestBody ProductRequestDTO dto) {

        var response = productService.update(id, dto);
        // retorna o curso response
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        // chama o service para deletar o product
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
