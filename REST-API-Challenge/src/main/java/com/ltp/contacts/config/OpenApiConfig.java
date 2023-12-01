package com.ltp.contacts.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
    @Bean // Bean para configurar o Swagger
    public  OpenAPI openAPI(){
        return new OpenAPI()
        .info(new Info()
        .title("Contacts API")
        .description("An API to manage contacts")
        .version("1.0.0"));
    }


}
