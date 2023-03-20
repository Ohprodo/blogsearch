package com.company.blogsearch.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoSearchResponseDto {
    private KkoMetaData meta;
    private KkoDocument documents;

    private static class KkoMetaData {
        private Integer total_count;
        private Integer pageable_count;
        private Boolean is_end;
    }

    private static class KkoDocument {
        private String title;
        private String contents;
        private String url;
        private String blogname;
        private String thumbnail;
        private String datetime;
    }
}
