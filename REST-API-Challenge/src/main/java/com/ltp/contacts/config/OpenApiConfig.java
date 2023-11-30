package com.ltp.contacts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {
    
    @Bean // Bean para configurar o Swagger
    public  OpenAPI openAPI(){
        return new OpenAPI();
    }


}
