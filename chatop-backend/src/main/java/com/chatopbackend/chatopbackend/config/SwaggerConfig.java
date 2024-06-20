package com.chatopbackend.chatopbackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public static final String BEARER_KEY = "bearer-key";
    public static final String SCHEME = "bearer";
    public static final String BEARER_FORMAT = "JWT";
    public static final String INFO_TITLE = "Chatop Backend";
    public static final String INFO_VERSION = "1.0";
    public static final String INFO_DESCRIPTION = "API for Chatop";

    /**
     * Configures the `OpenAPI` bean, which sets up the OpenAPI documentation for the application.
     *
     * @return the configured `OpenAPI` instance
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(BEARER_KEY, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(SCHEME)
                                .bearerFormat(BEARER_FORMAT)))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_KEY))
                .info(new Info()
                        .title(INFO_TITLE)
                        .version(INFO_VERSION)
                        .description(INFO_DESCRIPTION));
    }
}

