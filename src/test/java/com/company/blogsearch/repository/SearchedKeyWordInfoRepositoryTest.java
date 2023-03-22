package com.company.blogsearch.repository;

import com.company.blogsearch.entity.SearchedKeyWordInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@DataJpaTest
class SearchedKeyWordInfoRepositoryTest {

    private final SearchedKeyWordInfoRepository searchedKeyWordInfoRepository;

    SearchedKeyWordInfoRepositoryTest(@Autowired SearchedKeyWordInfoRepository searchedKeyWordInfoRepository) {
        this.searchedKeyWordInfoRepository = searchedKeyWordInfoRepository;
    }

    @DisplayName("키워드로 테이블 조회 테스트")
    @Test
    public void callFindByKeyWord() {
        Optional<SearchedKeyWordInfo> searchedKeyWordInfo = searchedKeyWordInfoRepository.findBykeyWord("더 글로리");
        searchedKeyWordInfo.ifPresentOrElse(keyWordInfo -> {
            log.info("결과 : {}", keyWordInfo.toString());
        }, () -> {
            log.info("조회 결과 없음");
        });
    }

    @DisplayName("인기 검색어 TOP 10 조회 테스트")
    @Test
    public void callFindTopTen() {
        List<SearchedKeyWordInfo> searchedKeyWordInfo = searchedKeyWordInfoRepository.findTop10ByOrderBySrchCntDesc();
        StringBuilder sb = new StringBuilder();
        for (SearchedKeyWordInfo info : searchedKeyWordInfo) {
            sb.append(info.toString()).append("\n");
        }
        log.info("결과 : {}", sb.toString());
    }
}