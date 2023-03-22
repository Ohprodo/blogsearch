package com.company.blogsearch.service;

import com.company.blogsearch.dto.kakao.KakaoSearchRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static com.company.blogsearch.constant.Const.KAKAO_SORT_ACCRC;

@Slf4j
@SpringBootTest
class SearchServiceTest {

    private final SearchService searchService;

    SearchServiceTest(@Autowired SearchService searchService) {
        this.searchService = searchService;
    }

    @DisplayName("SearchService 블로그 검색 메소드 테스트")
    @Test
    public void callSearchBlogByQuery() {
        KakaoSearchRequestDto kakaoSearchRequestDto = new KakaoSearchRequestDto();
        kakaoSearchRequestDto.setQuery("은행 이자율");
        kakaoSearchRequestDto.setPage(1);
        kakaoSearchRequestDto.setSize(10);
        kakaoSearchRequestDto.setSort(KAKAO_SORT_ACCRC);

        log.info("결과 : {}", searchService.searchBlogByQuery(kakaoSearchRequestDto).toString());
    }

    @DisplayName("SearchService 인기 검색어 조회 메소드 테스트")
    @Test
    public void callGetMostSearchedKeyWord() {
        log.info("결과 : {}", searchService.getMostSearchedKeyWord().toString());
    }

}