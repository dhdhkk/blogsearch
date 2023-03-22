package com.blogsearch.blog;


import com.blogsearch.dto.PopularKeywordResult;
import com.blogsearch.service.BlogService;
import com.validation.SortValid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@RequiredArgsConstructor
@RestController
@Validated
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/search/blogs")
    public ResponseEntity<Mono<?>> searchBlogs( @RequestParam String query,
                                                @RequestParam(required = false, defaultValue = "1") @Min(1) @Max(50) int page,
                                                @RequestParam(required = false, defaultValue = "10") @Min(1) @Max(50) int size,
                                                @RequestParam(required = false, defaultValue = "accuracy") @SortValid String sort) {

        Mono<?> blogSearchResultMono = blogService.searchBlogs(query, page, size, sort);
        return ResponseEntity.ok().body(blogSearchResultMono);
    }

    @GetMapping("/keywords/popular")
    public ResponseEntity<List<PopularKeywordResult>> PopularKeyword() {

        List<PopularKeywordResult> top10keywords = blogService.getTop10keywords();
        if(top10keywords.size()==0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(top10keywords);
    }

}
