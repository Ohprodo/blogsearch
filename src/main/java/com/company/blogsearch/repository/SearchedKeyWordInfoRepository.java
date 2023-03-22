package com.company.blogsearch.repository;

import com.company.blogsearch.entity.SearchedKeyWordInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchedKeyWordInfoRepository extends JpaRepository<SearchedKeyWordInfo, Long> {
    Optional<SearchedKeyWordInfo> findBykeyWord(String keyWord);
    List<SearchedKeyWordInfo> findTop10ByOrderBySrchCntDesc();
}
