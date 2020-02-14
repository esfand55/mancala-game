package com.dzone.mancala.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dzone.mancala.game.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Manacala Game Api service",
                "This application provides api for building Mancala game application.",
                "1.0.0",
                "TERMS OF SERVICE URL",
                new Contact("Esfandiyar", "http://linkedin.com/in/esfandiyar", "esfand55@gmail.com"),
                "MIT License",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
