package com.blogsearch.config;



import com.blogsearch.Exception.ClientError;
import com.blogsearch.Exception.ServerError;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    @Bean
    public WebClient webClientKakao() {
        ExchangeFilterFunction errorFilter = ExchangeFilterFunction
                .ofResponseProcessor(this::exchangeFilterResponseProcessor);

        final String KAKAO_BLOG_API_URL = "https://dapi.kakao.com";
        final String KAKAO_CLIENT_SECRET = "KakaoAK 332a90419ced30f3ff3eefe4b0ce8f3c";

        return WebClient.builder()
                .baseUrl(KAKAO_BLOG_API_URL)
                .defaultHeader("Authorization", KAKAO_CLIENT_SECRET)
                .filter(errorFilter)
                .build();
    }

    private Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatus status = (HttpStatus) response.statusCode();
        if (status.is4xxClientError()) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> {
                        System.out.println("body check = " + body);
                        return Mono.error(new ClientError(status, body));
                    });
        }
        return Mono.just(response);
    }


    @Bean
    public WebClient webClientNaver() {

        ExchangeFilterFunction errorFilter = ExchangeFilterFunction
                .ofResponseProcessor(this::exchangeFilterResponseProcessorNaver);

        final String NAVER_BLOG_API_URL = "https://openapi.naver.com";
        final String NAVER_CLIENT_ID = "orA_TyFVjHy379IFKB6b";
        final String NAVER_CLIENT_SECRET = "OkW6mVhnRn";

        return WebClient.builder()
                .baseUrl(NAVER_BLOG_API_URL)
                .defaultHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .defaultHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                .filter(errorFilter)
                .build();
    }

    private Mono<ClientResponse> exchangeFilterResponseProcessorNaver(ClientResponse response) {
        HttpStatus status = (HttpStatus) response.statusCode();
        if (status.is5xxServerError()) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> {
                        System.out.println("body = " + body);
                        return Mono.error(new ServerError(status, body));
                    });
        }
        if (status.is4xxClientError()) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> {
                        System.out.println("body = " + body);
                        return Mono.error(new ClientError(status, body));
                    });
        }
        return Mono.just(response);
    }

}
