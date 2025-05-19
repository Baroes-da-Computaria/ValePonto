package com.map.valeponto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valeponto/client")
public class ClientController {

    @GetMapping
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Hello from Vale Ponto");
    }
}
