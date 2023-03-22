package com.blogsearch.dto;

import com.blogsearch.blog.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BlogSearchResult {
    private PageInfo pageInfo;

    private List<Blog> blogs;
}
