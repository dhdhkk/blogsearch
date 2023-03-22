package com.blogsearch.domain;

import lombok.Builder;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "seach_keyword")
@Entity
public class SearchKeyword {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    String keyword;

    @Column(nullable = false)
    private Long count;

    @Builder
    public SearchKeyword(String keyword, Long count) {
        this.keyword = keyword;
        this.count = 1L;
    }

    public void addCount() {
        this.count += 1L;
    }
}
