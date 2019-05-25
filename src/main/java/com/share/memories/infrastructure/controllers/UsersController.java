package com.share.memories.infrastructure.controllers;

import com.share.memories.application.posts.dto.PostResponse;
import com.share.memories.application.users.SessionUtil;
import com.share.memories.application.users.UserContext;
import com.share.memories.application.users.UsersFacade;
import com.share.memories.application.users.dto.*;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersFacade usersFacade;
    private final SessionUtil sessionUtil;

    @PostMapping
    public AddUserResponse addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        log.info("Add user request: {}", addUserRequest);
        return usersFacade.addUserAndCreateSessionToken(addUserRequest);
    }

    @PostMapping("/token")
    public TokenResponse getSessionToken(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        log.info("Get session token for user: {}", loginUserRequest);
        return sessionUtil.getSessionToken(loginUserRequest);
    }

    @GetMapping("/account")
    public UserResponse getUser() {
        log.info("Get user profile request");
        UserContext userContext = sessionUtil.getUserContext();
        log.info("userContext:{}", userContext);
        return usersFacade.getUser(userContext);
    }

    @PostMapping("/account/followers")
    public Mono<UserResponse> addFollower(@Valid @RequestBody AddFollowerRequest request) {
        UserContext userContext = sessionUtil.getUserContext();
        log.info("Add follower request:{}, for user:{}", request.getUserUuid(), userContext);
        return usersFacade.addFollower(userContext, request.getUserUuid());
    }

    @GetMapping("/posts")
    public List<PostResponse> getAllPostsForUser() {
        UserContext userContext = sessionUtil.getUserContext();
        log.info("Get all posts for user:{}", userContext.getUuid());
        return usersFacade.getAllPostsForUser(userContext);

    }

    @GetMapping("/{uuid}/posts")
    public List<PostResponse> getAllPostsCreatedByUser(@PathVariable String uuid) {
        log.info("Get all posts  created by user:{}", uuid);
        return usersFacade.getAllPostsCreatedByUser(uuid);

    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
