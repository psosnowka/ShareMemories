package com.share.memories.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.UUID;

import static com.share.memories.infrastructure.rest.ValidationErrorMapper.map;

@Slf4j
@RestControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(HttpMessageNotReadableException ex) {
        final String uuidException = UUID.randomUUID()
                                         .toString();
        log.info("ERROR - " + uuidException + ", message: " + ex.getMessage(), ex);
        return new ApiResponse("Invalid JSON", uuidException);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleException(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        final String uuidException = UUID.randomUUID()
                                         .toString();
        log.info("ERROR - " + uuidException + ", message: " + ex.getMessage(), ex);
        List<ApiValidationError> validationErrors = map(ex.getBindingResult());
        return new ApiResponse<>(ApiErrorCode.VALIDATION_ERROR, "Some fields are invalid", validationErrors, uuidException);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleException(MissingServletRequestParameterException ex) {
        final String uuidException = UUID.randomUUID()
                                         .toString();
        log.info("ERROR - " + uuidException + ", message: " + ex.getMessage(), ex);
        return new ApiResponse<>(ApiErrorCode.VALIDATION_ERROR,
                                 "Required url parameter '" + ex.getParameterName() + "' is not present",
                                 null,
                                 uuidException);
    }

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> handleException(AppException ex) {
        final String uuidException = UUID.randomUUID()
                                         .toString();
        log.info("ERROR - " + uuidException + ", message: " + ex.getMessage(), ex);
        return new ResponseEntity<>(new ApiResponse<>(ex.getCode(), ex.getMessage(), null, uuidException), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleException(Exception ex) {
        final String uuidException = UUID.randomUUID()
                                         .toString();
        log.error("ERROR - " + uuidException + ", message: " + ex.getMessage(), ex);
        return new ApiResponse(ex.getMessage(), uuidException);
    }
}
