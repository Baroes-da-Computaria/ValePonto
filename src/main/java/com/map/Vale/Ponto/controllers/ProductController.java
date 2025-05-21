package com.map.Vale.Ponto.controllers;

import com.map.Vale.Ponto.model.product.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valeponto/product")
public class ProductController {

    @GetMapping
    public String getProduct() {
        return "Vamos ver se funciona bububu";
    }
}
