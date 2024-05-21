package com.tracking.management.system.trackingmicroservice.util.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
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
                                .version("1.0.0"))
                .addServersItem(new Server().url("http://localhost:7071/order-management-system/tracking-microservice"))
                .addServersItem(new Server().url("http://localhost:7079"));
    }
}
