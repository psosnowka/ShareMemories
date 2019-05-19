package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.users.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostsFacade {
    private final PostsService postsService;

    public PostContext addPost(AddPostRequest request, UserContext userContext) {
        return postsService.addPost(request, userContext).getPostContext();
    }
}
