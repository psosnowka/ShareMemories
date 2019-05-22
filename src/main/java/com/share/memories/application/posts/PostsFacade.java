package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.posts.dto.PostResponse;
import com.share.memories.application.users.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostsFacade {
    private final PostsService postsService;

    public PostResponse addPost(AddPostRequest request, UserContext userContext) {
        return postsService.addPost(request, userContext).getPostResponse();
    }

    public List<PostResponse> getAllPosts() {
        return postsService.getAllPostsForUsers()
                           .stream()
                           .map(post -> post.getPostResponse())
                           .collect(Collectors.toList());
    }
}
