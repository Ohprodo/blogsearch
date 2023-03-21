package com.company.blogsearch.dto.naver.client;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class NaverApiCallResponseDto {

    private int total;
    private int start;
    private int display;
    private String lastBuildDate;
    private List<Item> items;

    @Getter
    @ToString
    public static class Item {
        private String title;
        private String link;
        private String description;
        @SerializedName("bloggername")
        private String bloggerName;

        @SerializedName("bloggerlink")
        private String bloggerLink;

        @SerializedName("postdate")
        private String postDate;
    }
}
