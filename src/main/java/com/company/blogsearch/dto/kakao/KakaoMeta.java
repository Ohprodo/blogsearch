package com.company.blogsearch.dto.kakao;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoMeta {
    @SerializedName("total_count")
    private Integer totalCount;

    @SerializedName("pageable_count")
    private Integer pageableCount;

    @SerializedName("is_end")
    private Boolean isEnd;
}
