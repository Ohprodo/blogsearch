package com.company.blogsearch.service;

import com.company.blogsearch.dto.kakao.KakaoSearchRequestDto;
import com.company.blogsearch.dto.kakao.KakaoSearchResponseDto;
import com.company.blogsearch.dto.kakao.SearchedKeyWordMeta;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallRequestDto;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallResponseDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallRequestDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallResponseDto;
import com.company.blogsearch.entity.SearchedKeyWordInfo;
import com.company.blogsearch.exception.GeneralException;
import com.company.blogsearch.service.kakao.KakaoApiCallService;
import com.company.blogsearch.service.naver.NaverApiCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Slf4j
@Service
public class SearchService {

    private final KakaoApiCallService kakaoApiCallService;
    private final NaverApiCallService naverApiCallService;
    private final SrchKeyWordInfoService srchKeyWordInfoService;

    public SearchService(KakaoApiCallService kakaoApiCallService, NaverApiCallService naverApiCallService, SrchKeyWordInfoService srchKeyWordInfoService) {
        this.kakaoApiCallService = kakaoApiCallService;
        this.naverApiCallService = naverApiCallService;
        this.srchKeyWordInfoService = srchKeyWordInfoService;
    }

    public KakaoSearchResponseDto searchBlogByQuery(KakaoSearchRequestDto kakaoSearchRequestDto) {
        try {

            try {
                KakaoApiCallRequestDto clientRequestDto = new KakaoApiCallRequestDto(kakaoSearchRequestDto);
                KakaoApiCallResponseDto clientResponseDto = kakaoApiCallService.callSearchApi(clientRequestDto);

                checkKeyWordAndUpdate(kakaoSearchRequestDto.getQuery());
                return new KakaoSearchResponseDto(clientResponseDto);
            } catch (GeneralException e) {
                log.info("[SearchService][searchBlogByQuery][KakaoApiCall] GeneralException : {}", e.getErrorCode().toString());
            }

            NaverApiCallRequestDto naverClientRequestDto = new NaverApiCallRequestDto(kakaoSearchRequestDto);
            NaverApiCallResponseDto naverClientResponseDto = naverApiCallService.callSearchApi(naverClientRequestDto);
            checkKeyWordAndUpdate(kakaoSearchRequestDto.getQuery());
            return new KakaoSearchResponseDto(naverClientResponseDto);

        } catch (GeneralException e) {
            log.info("[SearchService][searchBlogByQuery][NaverApiCall] GeneralException : {}", e.getErrorCode().toString());
            throw e;
        }
    }
    public List<SearchedKeyWordMeta> getMostSearchedKeyWord() {
        try {
            List<SearchedKeyWordInfo> keyWordList = srchKeyWordInfoService.searchTopTenKeyWord();
            List<SearchedKeyWordMeta> resultList = new LinkedList<>();

            for (SearchedKeyWordInfo keyWordInfo : keyWordList) {
                resultList.add(SearchedKeyWordMeta.builder()
                        .keyWord(keyWordInfo.getKeyWord())
                        .srchCnt(keyWordInfo.getSrchCnt())
                        .build());
            }

            return resultList;
        } catch (GeneralException e) {
            log.info("[KeyWordCheckService][getMostSearchedKeyWord] GeneralException : {}", e.getErrorCode().toString());
            throw e;
        }
    }

    private boolean checkKeyWordAndUpdate(String query) {
        try {
            if (query.length() > 20) return false;

            String[] splitedQueryByBlank = query.split(" ");
            if (splitedQueryByBlank.length > 2) return false;

            srchKeyWordInfoService.upsertKeyWordInfo(query.trim());

            return true;
        } catch (GeneralException e) {
            log.info("[KeyWordCheckService][checkKeyWordAndUpdate] GeneralException : {}", e.getErrorCode().toString());
            throw e;
        }
    }

}
