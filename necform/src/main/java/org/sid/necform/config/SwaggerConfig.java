package org.sid.necform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI necformOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NECFORM API")
                        .description("API de gestion des formations")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("NECFORM Team")
                                .email("contact@necform.fr ")));
    }
}
