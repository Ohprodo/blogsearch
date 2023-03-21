package com.company.blogsearch.dto.kakao;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoDocument {
    private String title;

    private String contents;

    private String url;

    @SerializedName("blogname")
    private String blogName;

    @SerializedName("thumbnail")
    private String thumbNail;

    @SerializedName("datetime")
    private String dateTime;
}
