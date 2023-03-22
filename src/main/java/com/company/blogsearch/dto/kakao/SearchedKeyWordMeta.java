package com.company.blogsearch.dto.kakao;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchedKeyWordMeta {
    private String keyWord;
    private Long srchCnt;
}
