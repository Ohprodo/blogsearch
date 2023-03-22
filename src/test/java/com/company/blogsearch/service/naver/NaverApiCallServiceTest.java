package com.company.blogsearch.service.naver;

import com.company.blogsearch.dto.naver.client.NaverApiCallRequestDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverApiCallServiceTest {

    private final NaverApiCallService naverApiCallService;

    NaverApiCallServiceTest(@Autowired NaverApiCallService naverApiCallService) {
        this.naverApiCallService = naverApiCallService;
    }

    @DisplayName("NAVER 블로그 검색 API 테스트")
    @Test
    public void kakaoApiCallTest() {
        NaverApiCallRequestDto requestDto = new NaverApiCallRequestDto("spring boot",
                10, 1, "sim");
        NaverApiCallResponseDto responseDto = naverApiCallService.callSearchApi(requestDto);
        System.out.print(responseDto.toString());
    }
}