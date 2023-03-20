package com.company.blogsearch.controller;

import com.company.blogsearch.dto.CommonResponse;
import com.company.blogsearch.dto.kakao.KakaoSearchRequestDto;
import com.company.blogsearch.dto.kakao.KakaoSearchResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController("/v1")
public class SearchController {

    @PostMapping("/search/blog")
    public CommonResponse<KakaoSearchResponseDto> searchBlog(@Valid KakaoSearchRequestDto kakaoSearchRequestDto) {
        return CommonResponse.of(KakaoSearchResponseDto.builder().build());
    }

}
