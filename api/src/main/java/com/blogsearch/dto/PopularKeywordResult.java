package com.blogsearch.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PopularKeywordResult {

    private final String keyword;
    private final Long count;

    @Builder
    public PopularKeywordResult(String keyword, Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
