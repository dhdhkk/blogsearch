package com.blogsearch;

import com.blogsearch.domain.SearchKeyword;
import com.blogsearch.repository.SearchKeywordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApiApplicationTests {

    @Autowired
    private SearchKeywordRepository searchKeywordRepository;

    @Test
    public void save() {
        // given
        searchKeywordRepository.save(SearchKeyword.builder().keyword("test").build());

        // when
        List<SearchKeyword> searchKeyword = searchKeywordRepository.findAll();

        // then
        assertEquals(searchKeyword.get(0).getKeyword(), "test");

    }
}
