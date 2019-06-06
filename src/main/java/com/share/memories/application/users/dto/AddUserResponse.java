package com.share.memories.application.users.dto;

import com.share.memories.application.users.UserContext;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.Value;

@Value
public class AddUserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String userUuid;
    private String image;
    private String token;

    public static AddUserResponse of(UserContext userContext, TokenResponse tokenResponse) {
        return new AddUserResponse(userContext.getFirstName(),
                                   userContext.getLastName(),
                                   userContext.getEmail(),
                                   userContext.getUuid(),
                                   userContext.getAvatarUrl(),
                                   tokenResponse.getToken());
    }
}
