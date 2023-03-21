package com.company.blogsearch.dto.naver.client;

import com.company.blogsearch.dto.kakao.KakaoSearchRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.ToString;

import static com.company.blogsearch.constant.Const.*;

@ToString
@AllArgsConstructor
public class NaverApiCallRequestDto {

    @JsonProperty
    private String query;

    @JsonProperty
    private Integer display;

    @JsonProperty
    private Integer start;

    @JsonProperty
    private String sort;

    public NaverApiCallRequestDto(KakaoSearchRequestDto kakaoSearchRequestDto) {
        this.query = kakaoSearchRequestDto.getQuery();
        this.display = kakaoSearchRequestDto.getSize() <= 50 ? kakaoSearchRequestDto.getSize() : 50;
        this.start = kakaoSearchRequestDto.getPage() <= 50 ? kakaoSearchRequestDto.getPage() : 50;
        this.sort = kakaoSearchRequestDto.getSort().equals(KAKAO_SORT_RCNC) ? NAVER_SORT_RCNC : NAVER_SORT_ACCRC;
    }
}
