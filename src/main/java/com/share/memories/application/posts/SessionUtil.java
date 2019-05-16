package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddUserRequest;
import com.share.memories.application.posts.dto.LoginUserRequest;
import com.share.memories.application.util.SecurityUtil;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SessionUtil {
    private final UserApplicationService userApplicationService;
    private final SecurityUtil securityUtil;

    public Mono<TokenResponse> createUserAndSessionToken(AddUserRequest addUserRequest) {
        return Mono.fromCallable(() -> userApplicationService.addUser(addUserRequest))
                   .map(user -> securityUtil.createSessionToken(user.getEmail()));
    }

    public Mono<TokenResponse> getSessionToken(LoginUserRequest loginUserRequest) {
        return Mono.fromCallable(() -> userApplicationService.getUserByCredentials(loginUserRequest))
                   .map(userContext -> securityUtil.createSessionToken(userContext.getEmail()));
    }
}
