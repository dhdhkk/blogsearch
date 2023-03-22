package com.blogsearch.searchsource;

import com.blogsearch.dto.BlogSearchResult;
import reactor.core.publisher.Mono;

public interface SearchSource {
    Mono<BlogSearchResult> searchBlogs(String query, int page, int size, String sort);
}
