package com.company.blogsearch.service;

import com.company.blogsearch.constant.ErrorCode;
import com.company.blogsearch.entity.SearchedKeyWordInfo;
import com.company.blogsearch.exception.GeneralException;
import com.company.blogsearch.repository.SearchedKeyWordInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class SrchKeyWordInfoService {

    private final SearchedKeyWordInfoRepository searchedKeyWordInfoRepository;

    public List<SearchedKeyWordInfo> searchTopTenKeyWord() {
        try {
            return searchedKeyWordInfoRepository.findTop10ByOrderBySrchCntDesc();
        } catch (Exception e) {
            log.error("[SrchKeyWordInfoService][searchTopTenKeyWord] Exception : {}", e.getMessage());
            throw new GeneralException(ErrorCode.DB_SELECT_ERROR, e);
        }
    }

    public boolean upsertKeyWordInfo(String query) {
        try {
            if (query == null || query.equals("")) return false;

            searchedKeyWordInfoRepository.findBykeyWord(query)
                    .ifPresentOrElse(keyWord -> {
                        keyWord.setSrchCnt(keyWord.getSrchCnt() + 1);
                        searchedKeyWordInfoRepository.save(keyWord);
                        }, () -> {
                        SearchedKeyWordInfo newKeyWordInfo = new SearchedKeyWordInfo();
                        newKeyWordInfo.setKeyWord(query);
                        newKeyWordInfo.setSrchCnt(1L);
                        searchedKeyWordInfoRepository.save(newKeyWordInfo);
                    });

            return true;
        } catch (Exception e) {
            log.error("[SrchKeyWordInfoService][modifyKeyWordInfo] Exception : {}", e.getMessage());
            throw new GeneralException(ErrorCode.DB_UPDATE_ERROR, e);
        }
    }
}
