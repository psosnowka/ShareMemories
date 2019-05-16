package com.share.memories.infrastructure.controllers;

import com.share.memories.application.posts.SessionUtil;
import com.share.memories.application.posts.dto.AddUserRequest;
import com.share.memories.application.posts.dto.LoginUserRequest;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final SessionUtil sessionUtil;

    @PostMapping("/users")
    public Mono<TokenResponse> addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        log.info("Add user request: {}", addUserRequest);
        return sessionUtil.createUserAndSessionToken(addUserRequest);
    }

    @PostMapping("/token")
    public Mono<TokenResponse> getSessionToken(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        log.info("Get session token for user: {}", loginUserRequest);
        return sessionUtil.getSessionToken(loginUserRequest);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
