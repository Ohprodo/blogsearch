package com.company.blogsearch.service.kakao;

import com.company.blogsearch.dto.kakao.client.KakaoApiCallRequestDto;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KakaoApiCallServiceTest {

    private final KakaoApiCallService kakaoApiCallService;

    KakaoApiCallServiceTest(@Autowired KakaoApiCallService kakaoApiCallService) {
        this.kakaoApiCallService = kakaoApiCallService;
    }

    @DisplayName("KAKAO 블로그 검색 API 테스트")
    @Test
    public void kakaoApiCallTest() {
        KakaoApiCallRequestDto requestDto = new KakaoApiCallRequestDto("spring boot",
                "recency", 1, 10);
        KakaoApiCallResponseDto responseDto = kakaoApiCallService.callSearchApi(requestDto);
        System.out.print(responseDto.toString());
    }

}