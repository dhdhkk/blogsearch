package com.blogsearch.searchsource;

import com.blogsearch.blog.Blog;
import com.blogsearch.Exception.ServerErrorException;
import com.blogsearch.dto.BlogSearchResult;
import com.blogsearch.dto.KakaoBlogSearchResult;
import com.blogsearch.dto.PageInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class KakaoSearchSource implements SearchSource{

    private final WebClient webClientKakao;


    @Override
    public Mono<BlogSearchResult> searchBlogs(String query, int page, int size, String sort) {
        return webClientKakao.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/search/blog")
                        .queryParam("query", query)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .queryParam("sort", sort)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, this::handle5xxErrorResponse)
                .bodyToMono(KakaoBlogSearchResult.class)
                .map(response -> new BlogSearchResult(
                        new PageInfo(response.getMeta().getPageable_count(),
                                (int)Math.ceil( (double) response.getMeta().getPageable_count() / size) ,
                                page),
                        response.getDocuments().stream()
                                .map(document -> new Blog(
                                        document.getBlogname(),
                                        document.getContents(),
                                        document.getDatetime(),
                                        document.getThumbnail(),
                                        document.getTitle(),
                                        document.getUrl()))
                                .collect(Collectors.toList())));
    }

    public Mono<RuntimeException> handle5xxErrorResponse(ClientResponse clientResponse) {
        Mono<String> errorResponse = clientResponse.bodyToMono(String.class);
        return errorResponse.flatMap((message) -> {
            System.out.println("ErrorResponse Code is " + clientResponse.statusCode() + " and the exception message is : " + message);
            return Mono.error(new ServerErrorException(message));
        });
    }

}
