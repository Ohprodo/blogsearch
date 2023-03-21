package com.company.blogsearch.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "api.naver")
public class NaverApiProperties {
    private String baseUrl;
    private String searchApi;
    private String clientId;
    private String clientSecret;
}
