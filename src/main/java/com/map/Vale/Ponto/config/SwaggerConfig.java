package com.map.Vale.Ponto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MAP - APP VALE PONTO")
                        .version("1.0")
                        .description("Gerenciamento de fidelidade, permitindo que empresas cadastrem recompensas e que clientes acumulem e troquem pontos por produtos" ));
    }
}