package com.blogsearch.repository;

import com.blogsearch.domain.SearchKeyword;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Long> {

    Optional<SearchKeyword> findByKeyword(String keyword);

    @Cacheable(value = "getCache", key = "0")
    List<SearchKeyword> findTop10ByOrderByCountDesc();

}
