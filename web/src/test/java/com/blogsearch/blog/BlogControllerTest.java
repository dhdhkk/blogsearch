package com.blogsearch.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testSearchBlogs() {
        webTestClient.get()
                .uri("/search/blogs")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    public void testGetTop10SearchKeywords() {
        webTestClient.get()
                .uri("/search/keywords/popular")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }


}