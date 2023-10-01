package com.proyect.employee.employee.config;

import com.proyect.employee.employee.database.config.EnvironmentConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Autowired
    EnvironmentConfig environmentConfig;

    @Bean
    public OpenAPI myOpenAPI() {
        String description = String.format("Credentials: </br>{" +
                        "</br>&nbsp;&nbsp;\"username\": \"%s\"," +
                        "</br>&nbsp;&nbsp;\"password\": \"%s\"</br>}",
                environmentConfig.getUser_admin(), environmentConfig.getPass_admin());

        return new OpenAPI().info(
                new Info()
                        .title("Microservice for employee management")
                        .description(description)
                        .version("0.11")
        );
    }
}
