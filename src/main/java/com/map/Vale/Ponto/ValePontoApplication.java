package com.map.Vale.Ponto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ValePontoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValePontoApplication.class, args);
    }
}
