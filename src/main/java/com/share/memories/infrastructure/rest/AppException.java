package com.share.memories.infrastructure.rest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AppException extends RuntimeException {
    private ApiErrorCode code;
    private HttpStatus httpStatus;

    public AppException(ApiErrorCode code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public AppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
