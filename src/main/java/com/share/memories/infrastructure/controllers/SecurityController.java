package com.share.memories.infrastructure.controllers;

import com.share.memories.application.users.UserFacade;
import com.share.memories.application.users.dto.AddUserRequest;
import com.share.memories.application.users.dto.LoginUserRequest;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserFacade userFacade;

    @PostMapping("/users")
    public Mono<TokenResponse> addUser(@Validated @RequestBody AddUserRequest addUserRequest) {
        log.info("Add user request: {}", addUserRequest);
        return userFacade.createUserAndSessionToken(addUserRequest);
    }

    @PostMapping("/token")
    public Mono<TokenResponse> getSessionToken(@RequestBody LoginUserRequest loginUserRequest) {
        log.info("Get session token for user: {}", loginUserRequest);
        return userFacade.getSessionToken(loginUserRequest);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
