package com.product.inventory.management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(
                    new Info()
                        .title("Microservice for inventory management")
                        .description("....")
                        .version("0.11")
        );
    }
}
