package com.blogsearch.repository;

import com.blogsearch.domain.SearchKeyword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class SearchKeywordRepositoryTest {

    @Autowired
    private SearchKeywordRepository searchKeywordRepository;

    @Test
    @Transactional
    public void add () {

        // given
        searchKeywordRepository.save(SearchKeyword.builder().keyword("test").build());

        // when
        List<SearchKeyword> searchKeyword = searchKeywordRepository.findAll();

        // then
        assertEquals(searchKeyword.get(0).getKeyword(), "test");

    }

    @Test
    public void findTop10ByOrderByCountDesc() {
        // given

        // when
        List<SearchKeyword> searchKeyword = searchKeywordRepository.findAll();

        // then
        assertEquals(searchKeyword.get(0).getKeyword(), "test");
    }
}