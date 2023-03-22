package com.company.blogsearch.controller;

import com.company.blogsearch.dto.CommonResponse;
import com.company.blogsearch.dto.kakao.KakaoSearchRequestDto;
import com.company.blogsearch.dto.kakao.KakaoSearchResponseDto;
import com.company.blogsearch.dto.kakao.SearchedKeyWordMeta;
import com.company.blogsearch.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="검색 API")
@RequestMapping("/v1")
@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @Operation(summary = "블로그 검색 API", description = "카카오 API를 연동한 블로그 검색 API")
    @PostMapping("/search/blog")
    public CommonResponse<KakaoSearchResponseDto> searchBlog(@Valid @RequestBody KakaoSearchRequestDto kakaoSearchRequestDto) {
        return CommonResponse.of(searchService.searchBlogByQuery(kakaoSearchRequestDto));
    }

    @Operation(summary = "인기검색어 TOP 10 API", description = "인기검색어 TOP 10")
    @GetMapping("/search/top/keyword")
    public CommonResponse<List<SearchedKeyWordMeta>> seasrchTopTenKeyWord() {
        return CommonResponse.of(searchService.getMostSearchedKeyWord());
    }
}
