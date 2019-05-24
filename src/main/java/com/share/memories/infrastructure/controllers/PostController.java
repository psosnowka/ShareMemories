package com.share.memories.infrastructure.controllers;

import com.share.memories.application.posts.PostsFacade;
import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.posts.dto.PostResponse;
import com.share.memories.application.posts.dto.UpdatePostRequest;
import com.share.memories.application.users.SessionUtil;
import com.share.memories.application.users.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {
    private final SessionUtil sessionUtil;
    private final PostsFacade postsFacade;

    @PostMapping
    public PostResponse addPost(@RequestBody AddPostRequest request) {
        UserContext userContext = sessionUtil.getUserContext();
        log.info("Add post request: {} in user context: {}", request, userContext.getEmail());
        return postsFacade.addPost(request, userContext);
    }

    @GetMapping
    public List<PostResponse> getAllPostsForUser() {
        UserContext userContext = sessionUtil.getUserContext();
        log.info("Get all posts for user:{}", userContext.getEmail());
        return postsFacade.getAllPosts();
    }

    @PutMapping("/{uuid}")
    public PostResponse updatePost(@Valid @RequestBody UpdatePostRequest request, @PathVariable String uuid) {
        log.info("Update post request:{}", request);
        UserContext userContext = sessionUtil.getUserContext();
        request.setPostUuid(uuid);
        return postsFacade.updatePost(request, userContext);
    }

    @GetMapping("/{uuid}")
    public PostResponse getPost(@PathVariable String uuid) {
        log.info("Get post by uuid:{}", uuid);
        return postsFacade.getPostByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletePost(@PathVariable("uuid") String uuid) {
        UserContext userContext = sessionUtil.getUserContext();
        postsFacade.delete(userContext, uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
