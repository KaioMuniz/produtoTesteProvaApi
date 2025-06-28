package br.com.kaiomuniz.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Produtos - Kaio Muniz")
                .version("v1")
                .description("Documentação da API de Produtos criada com Spring Boot e PostgreSQL.")
                .contact(new Contact()
                    .name("Kaio Muniz")
                    .email("kkaioribeiro@gmail.com")
                    .url("https://www.kaiomuniz.com.br")
                )
            );
    }
}
// This configuration class sets up Swagger for API documentation.