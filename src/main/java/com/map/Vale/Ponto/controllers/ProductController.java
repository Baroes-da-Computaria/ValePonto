package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.product.Product;
import com.map.Vale.Ponto.model.product.ProductDTO;
import com.map.Vale.Ponto.services.ProductServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valeponto/product")
public class ProductController {

    private final ProductServices productServices;

    ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable("id") Long id) {
        return productServices.findById(id);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        return productServices.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product create(@RequestBody ProductDTO dto){
        return productServices.save(dto);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product update(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
        return productServices.update(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        productServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
