package com.blogsearch.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
public class KakaoBlogSearchResult {

    List<Documents> documents;
    Meta meta;

    @Data
    public static class Documents {

        private String blogname;
        private String contents;
        private String datetime;
        private String thumbnail;
        private String title;
        private String url;
    }


    @Data
    public static class Meta {

        private boolean is_end;
        private int pageable_count;
        private int total_count;
    }


}
