package com.share.memories.infrastructure.rest;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class ApiValidationError {

    private String field;
    private String message;

}
