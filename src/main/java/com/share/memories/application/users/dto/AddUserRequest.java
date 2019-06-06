package com.share.memories.application.users.dto;

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
    private final String firstName;
    private final String lastName;
    private final String image;
}
