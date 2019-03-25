package com.share.memories.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Getter
public class ApiResponse<T> {
    @Setter
    private ApiErrorCode status;
    private String message;
    private T data;
    private String traceId;
    @DateTimeFormat(pattern = " yyyy-MM-dd'T'hh:mm:ss.SSS")
    private LocalDateTime date = LocalDateTime.now();

    public ApiResponse(String message, String traceId) {
        this.message = message;
        this.traceId = traceId;
    }
    public ApiResponse(ApiErrorCode status, String message, String traceId) {
        this.status = status;
        this.message = message;
        this.traceId = traceId;
    }

    public ApiResponse(ApiErrorCode status, String message, T data, String traceId) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
    }
}
