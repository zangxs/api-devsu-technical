package com.brayanpv.app.infrastructure.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI clienteServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cliente Service API")
                        .description("Microservicio de gestión de cuentas - movimientos y generacion de reportes - Prueba técnica Devsu")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Brayan")
                                .url("https://github.com/zangxs/api-devsu-technical")));
    }
}
