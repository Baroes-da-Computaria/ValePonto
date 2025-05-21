package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.product.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valeponto/product")
public class ProductController {

    @GetMapping
    public Product getProduct() {
        return new Product(1L, "Produto 1", "Categoria 1", "Descrição do Produto 1", 10.0, "http://example.com/image1.jpg");
    }
}
