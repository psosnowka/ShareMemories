package com.share.memories.infrastructure.controllers;

import com.share.memories.application.posts.PostContext;
import com.share.memories.application.posts.PostsFacade;
import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.users.SessionUtil;
import com.share.memories.application.users.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {
    private final SessionUtil sessionUtil;
    private final PostsFacade postsFacade;

    @PostMapping
    public PostContext addPost(AddPostRequest request) {
        UserContext userContext = sessionUtil.getUserContext();
        log.info("Add post request: {} in user context: {}", request, userContext.getEmail());
        return postsFacade.addPost(request, userContext);
    }

}
