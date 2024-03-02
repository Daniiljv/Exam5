package com.jdbc.exam5.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configuration() {
        return new OpenAPI()
                .info(new Info()
                        .title("Exam5")
                        .description("Exam5")
                        .version("1.2.0")
                        .contact(new Contact().name("Daniil")));
    }

}