package com.blogsearch.searchsource;

import com.blogsearch.blog.Blog;
import com.blogsearch.dto.BlogSearchResult;
import com.blogsearch.dto.NaverBlogSearchResult;
import com.blogsearch.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class NaverSearchSoruce implements SearchSource{

    private final WebClient webClientNaver;

    @Override
    public Mono<BlogSearchResult> searchBlogs(String query, int page, int size, String sort) {
        return webClientNaver.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/blog.json")
                        .queryParam("query", query)
                        .queryParam("display", size)
                        .queryParam("start", page)
                        .queryParam("sort", sort.equals("accuracy") ? "sim": "date")
                        .build())
                .retrieve()
                .bodyToMono(NaverBlogSearchResult.class)
                .map(response -> new BlogSearchResult(
                        new PageInfo(response.getTotal(),
                                (int)Math.ceil( (double) response.getTotal() / response.getDisplay()),
                                response.getStart()),
                        response.getItems().stream()
                                .map(item -> new Blog(
                                        item.getTitle(),
                                        item.getDescription(),
                                        item.getPostdate(),
                                        null,
                                        item.getTitle(),
                                        item.getLink()))
                                .collect(Collectors.toList())));
    }
}
