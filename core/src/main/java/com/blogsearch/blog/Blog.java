package com.blogsearch.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Blog {
    private String blogname;
    private String contents;
    private String datetime;
    private String thumbnail;
    private String title;
    private String url;
}
