package com.blogsearch.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse {
    @JsonProperty(value = "Message")
    private String message;

    @JsonProperty(value = "Detail")
    private String detail;
}
