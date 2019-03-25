package com.share.memories.infrastructure.rest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

class ValidationErrorMapper {

    public static List<ApiValidationError> map(BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        return allErrors.stream()
                        .map(ValidationErrorMapper::mapToApiValidationError)
                        .collect(Collectors.toList());
    }

    private static ApiValidationError mapToApiValidationError(ObjectError objectError) {
        String defaultMessage = objectError.getDefaultMessage();
        String field = "field";
        if (objectError instanceof FieldError) {
            field = ((FieldError) objectError).getField();
        }
        return ApiValidationError.builder()
                                 .field(field)
                                 .message(defaultMessage)
                                 .build();
    }
}
