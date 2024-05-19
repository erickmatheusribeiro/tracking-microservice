package com.tracking.management.system.trackingmicroservice.util.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Microsserviço para controle entregas")
                                .description("APIs para cadastro e atualização de entregas")
                                .version("1.0.0")
                );
    }
}
