package com.map.Vale.Ponto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valeponto/client")
public class ClientController {
    // todo: colocar o swagger
    @GetMapping
    public ResponseEntity<String> teste() {
        // todo implementar o endpoint
        return ResponseEntity.ok("Hello from Vale Ponto");
    }
}
