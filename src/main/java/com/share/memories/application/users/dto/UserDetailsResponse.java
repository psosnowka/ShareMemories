package com.share.memories.application.users.dto;

import com.share.memories.application.users.UserContext;
import lombok.Value;

@Value
public class UserDetailsResponse {
    private final String uuid;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String imageUrl;
    private final Boolean isFollowing;

    public static UserDetailsResponse withIsFollowingFalse(UserContext userContext) {
        return new UserDetailsResponse(userContext.getUuid(),
                                       userContext.getEmail(),
                                       userContext.getFirstName(),
                                       userContext.getLastName(),
                                       userContext.getAvatarUrl(),
                                       Boolean.FALSE);
    }

    public static UserDetailsResponse withIsFollowingTrue(UserContext userContext) {
        return new UserDetailsResponse(userContext.getUuid(),
                                       userContext.getEmail(),
                                       userContext.getFirstName(),
                                       userContext.getLastName(),
                                       userContext.getAvatarUrl(),
                                       Boolean.TRUE);
    }

}
