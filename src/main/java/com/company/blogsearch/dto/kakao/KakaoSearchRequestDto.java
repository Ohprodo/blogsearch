package com.company.blogsearch.dto.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class KakaoSearchRequestDto {

    @NotBlank
    private String query;

    @Pattern(regexp = "(^accuracy$|^recency$)")
    private String sort;

    @Min(1)
    @Max(50)
    @NotNull
    private Integer page;

    @Min(1)
    @Max(50)
    @NotNull
    private Integer size;
}
