package com.blogsearch.service;



import com.blogsearch.domain.SearchKeyword;
import com.blogsearch.dto.BlogSearchResult;
import com.blogsearch.dto.PopularKeywordResult;
import com.blogsearch.repository.SearchKeywordRepository;
import com.blogsearch.searchsource.SearchSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final SearchSource kakaoSearchSource;

    private final SearchSource naverSearchSoruce;

    private final SearchKeywordRepository searchKeywordRepository;

    @Transactional
    public Mono<?> searchBlogs(String query, int page, int size, String sort) {
        searchKeywordRepository.findByKeyword(query)
                .map(keyword -> {
                    keyword.addCount();
                    return keyword;
                })
                .orElseGet(() -> searchKeywordRepository.save(SearchKeyword.builder().keyword(query).build()));

        return kakaoSearchSource.searchBlogs(query, page, size, sort).onErrorResume(throwable -> {
            System.out.println("kakao SearchSource Exception");

            return naverSearchSoruce.searchBlogs(query, page, size, sort);
        });
    }

    public List<PopularKeywordResult> getTop10keywords() {
        List<SearchKeyword> top10ByOrderByCountDesc = searchKeywordRepository.findTop10ByOrderByCountDesc();

        List<PopularKeywordResult> popularKeywordResults = new ArrayList<>();
        for (SearchKeyword searchKeyword : top10ByOrderByCountDesc) {
            popularKeywordResults.add(PopularKeywordResult.builder().keyword(searchKeyword.getKeyword()).count(searchKeyword.getCount()).build());
        }

        return popularKeywordResults;
    }

}
