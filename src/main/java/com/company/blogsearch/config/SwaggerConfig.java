package com.company.blogsearch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("blog search")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("")
                        .description("")
                        .version(""));
    }
}
