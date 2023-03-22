package com.company.blogsearch.properties;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ApiPropertiesTest {
    
    private final KakaoApiProperties kakaoApiProperties;
    private final NaverApiProperties naverApiProperties;

    ApiPropertiesTest(@Autowired KakaoApiProperties kakaoApiProperties,
                      @Autowired NaverApiProperties naverApiProperties) {
        this.kakaoApiProperties = kakaoApiProperties;
        this.naverApiProperties = naverApiProperties;
    }

    @DisplayName("application-api.yaml resource 가져오기 테스트")
    @Test
    public void readApiResources() {
        log.info("kakao properties : {}", kakaoApiProperties.toString());
        log.info("naver properties : {}", naverApiProperties.toString());
    }
}