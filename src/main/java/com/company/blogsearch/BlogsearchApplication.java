package com.company.blogsearch;

import com.company.blogsearch.properties.KakaoApiProperties;
import com.company.blogsearch.properties.NaverApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableConfigurationProperties({KakaoApiProperties.class, NaverApiProperties.class})
@SpringBootApplication
public class BlogsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsearchApplication.class, args);
	}

}
