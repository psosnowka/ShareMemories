package com.share.memories.application.users.dto;

import lombok.Value;

import java.util.Set;

@Value
public class UserResponse {
    private final String uuid;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String image;
    private final Set<User> followers;

    @Value
    public static class User {
        private final String uuid;
        private final String email;
        private final String firstName;
        private final String lastName;
        private final String image;
    }
}
