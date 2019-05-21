package com.share.memories.application.users;

import com.share.memories.application.users.dto.AddUserRequest;
import com.share.memories.application.users.dto.UserResponse;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsersFacade {
    private final UsersService usersService;

    public Mono<TokenResponse> addUserAndCreateSessionToken(AddUserRequest addUserRequest) {
        return usersService.addUserAndGetSessionToken(addUserRequest);
    }

    public Mono<UserResponse> addFollower(UserContext userContext, String userUuid) {
        return usersService.addFolower(userContext, userUuid)
                           .map(AppUser::getUserResponse);
    }

    public Mono<UserResponse> getUser(UserContext userContext) {
        return usersService.getUser(userContext)
                           .map(AppUser::getUserResponse);
    }

}
