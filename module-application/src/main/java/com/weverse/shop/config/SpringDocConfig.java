package com.weverse.shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Weverse Shop API Document",
                version = "v0.0.1"
        )
)
public class SpringDocConfig {

}

