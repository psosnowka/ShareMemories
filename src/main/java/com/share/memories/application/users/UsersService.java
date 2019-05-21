package com.share.memories.application.users;

import com.share.memories.application.users.dto.AddUserRequest;
import com.share.memories.application.util.SecurityUtil;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class UsersService {
    private final UserApplicationService userApplicationService;
    private final SecurityUtil securityUtil;

    Mono<TokenResponse> addUserAndGetSessionToken(AddUserRequest addUserRequest) {
        return Mono.fromCallable(() -> userApplicationService.addUser(addUserRequest))
                   .map(user -> securityUtil.createSessionToken(user.getEmail()));
    }

    Mono<AppUser> addFolower(UserContext userContext, String userUuid) {
        return Mono.fromCallable(() -> userApplicationService.addFollower(userContext, userUuid));
    }

    Mono<AppUser> getUser(UserContext userContext) {
        return Mono.fromCallable(() -> userApplicationService.getUserByEmail(userContext.getEmail()));
    }

}
