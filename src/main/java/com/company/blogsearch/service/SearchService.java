package com.company.blogsearch.service;

import com.company.blogsearch.dto.kakao.KakaoSearchRequestDto;
import com.company.blogsearch.dto.kakao.KakaoSearchResponseDto;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallRequestDto;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallResponseDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallRequestDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallResponseDto;
import com.company.blogsearch.exception.GeneralException;
import com.company.blogsearch.service.kakao.KakaoApiCallService;
import com.company.blogsearch.service.naver.NaverApiCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SearchService {

    private final KakaoApiCallService kakaoApiCallService;
    private final NaverApiCallService naverApiCallService;
    private final KeyWordCheckService keyWordCheckService;

    public SearchService(KakaoApiCallService kakaoApiCallService, NaverApiCallService naverApiCallService, KeyWordCheckService keyWordCheckService) {
        this.kakaoApiCallService = kakaoApiCallService;
        this.naverApiCallService = naverApiCallService;
        this.keyWordCheckService = keyWordCheckService;
    }

    public KakaoSearchResponseDto searchBlogByQuery(KakaoSearchRequestDto kakaoSearchRequestDto) {
        try {

            try {
                keyWordCheckService.checkKeyWordAndUpdate(kakaoSearchRequestDto.getQuery());

                KakaoApiCallRequestDto clientRequestDto = new KakaoApiCallRequestDto(kakaoSearchRequestDto);
                KakaoApiCallResponseDto clientResponseDto = kakaoApiCallService.callSearchApi(clientRequestDto);
                return new KakaoSearchResponseDto(clientResponseDto);
            } catch (GeneralException e) {
                log.info("[SearchService][searchBlogByQuery][KakaoApiCall] GeneralException : {}", e.getErrorCode().toString());
            }

            NaverApiCallRequestDto naverClientRequestDto = new NaverApiCallRequestDto(kakaoSearchRequestDto);
            NaverApiCallResponseDto naverClientResponseDto = naverApiCallService.callSearchApi(naverClientRequestDto);
            return new KakaoSearchResponseDto(naverClientResponseDto);

        } catch (GeneralException e) {
            log.info("[SearchService][searchBlogByQuery][NaverApiCall] GeneralException : {}", e.getErrorCode().toString());
            throw e;
        }
    }
}
