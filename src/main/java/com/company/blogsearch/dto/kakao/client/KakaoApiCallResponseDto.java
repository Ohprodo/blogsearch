package com.company.blogsearch.dto.kakao.client;

import com.company.blogsearch.dto.kakao.KakaoDocument;
import com.company.blogsearch.dto.kakao.KakaoMeta;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class KakaoApiCallResponseDto {
    private KakaoMeta meta;
    private List<KakaoDocument> documents;
}
