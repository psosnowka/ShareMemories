package com.share.memories.application.posts.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Value
public class AddUserRequest {
    @Email
    @NotEmpty
    private final String email;
    @NotEmpty
    private final String password;
    @NotEmpty
    private final String firstName;
    @NotEmpty
    private final String lastName;
    private final String photoUrl;
}
