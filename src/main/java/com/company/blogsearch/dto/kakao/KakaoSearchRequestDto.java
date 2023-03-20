package com.company.blogsearch.dto.kakao;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class KakaoSearchRequestDto {

    @NotBlank
    private String query;
    private String sort;

    @Min(1)
    @Max(50)
    private Integer page;

    @Min(1)
    @Max(50)
    private Integer size;
}
