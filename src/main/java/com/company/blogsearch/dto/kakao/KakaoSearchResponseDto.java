package com.company.blogsearch.dto.kakao;

import com.company.blogsearch.dto.kakao.client.KakaoApiCallResponseDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallResponseDto;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoSearchResponseDto {
    private KakaoMeta meta;
    private List<KakaoDocument> documents;

    public KakaoSearchResponseDto(KakaoApiCallResponseDto clientResponseDto) {
        this.meta = clientResponseDto.getMeta();
        this.documents = clientResponseDto.getDocuments();
    }

    public KakaoSearchResponseDto(NaverApiCallResponseDto naverClientResponseDto) {

        this.meta.setIsEnd(naverClientResponseDto.getStart() == 50);
        this.meta.setTotalCount(naverClientResponseDto.getTotal());
    }
}
