package com.blogsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class PageInfo {
    private int totalCount;
    private int totalPages;
    private int currentPage;

}
