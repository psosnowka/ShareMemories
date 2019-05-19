package com.share.memories.application.users;

import com.share.memories.application.users.dto.AddUserRequest;
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

}
