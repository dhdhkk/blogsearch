package com.blogsearch.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

//4xx번대 에러 처리
@Getter
public class ClientError extends RuntimeException{
    private HttpStatus status;
    private String body;

    public ClientError(HttpStatus status, String body) {
        this.status = status;
        this.body = body;
    }
}