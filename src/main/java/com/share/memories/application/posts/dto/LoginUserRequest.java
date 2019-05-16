package com.share.memories.application.posts.dto;

import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@ToString(exclude = "password")
public class LoginUserRequest {
    @NotEmpty
    private final String email;
    @NotEmpty
    private final String password;
}
