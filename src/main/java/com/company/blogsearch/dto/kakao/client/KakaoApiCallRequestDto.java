package com.company.blogsearch.dto.kakao.client;

import com.company.blogsearch.dto.kakao.KakaoSearchRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class KakaoApiCallRequestDto {

    @JsonProperty
    private String query;

    @JsonProperty
    private String sort;

    @JsonProperty
    private Integer page;

    @JsonProperty
    private Integer size;

    public KakaoApiCallRequestDto(KakaoSearchRequestDto kakaoSearchRequestDto){
        this.query = kakaoSearchRequestDto.getQuery();
        this.sort = kakaoSearchRequestDto.getSort();
        this.page = kakaoSearchRequestDto.getPage();
        this.size = kakaoSearchRequestDto.getSize();
    }

}
