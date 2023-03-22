package com.blogsearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class NaverBlogSearchResult {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;

    List<item> items;

    @Data
    public static class item {

        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;
        private String postdate;
    }
}
