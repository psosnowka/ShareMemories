package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.posts.dto.PostResponse;
import com.share.memories.application.posts.dto.UpdatePostRequest;
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

    public List<PostResponse> getAllPublicPosts() {
        return postsService.getAllPublicPosts()
                           .stream()
                           .map(Post::getPostResponse)
                           .collect(Collectors.toList());
    }

    public List<PostResponse> getAllPostsForUsers(List<String> users) {
        return postsService.getAllPostsForUsers(users)
                           .stream()
                           .map(Post::getPostResponse)
                           .collect(Collectors.toList());
    }

    public PostResponse updatePost(UpdatePostRequest request, UserContext userContext) {
        return postsService.updatePost(request, userContext)
                           .getPostResponse();
    }

    public PostResponse getPostByUuid(String uuid) {
        return postsService.getPostByUuid(uuid).getPostResponse();
    }

    public void delete(UserContext userContext, String postUuid) {
        postsService.delete(userContext, postUuid);
    }
}
