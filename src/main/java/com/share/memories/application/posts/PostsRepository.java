package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.users.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
class PostsRepository {
    private final PostJpaRepository postJpaRepository;

    Post addPost(AddPostRequest addPostRequest, UserContext userContext) {
        return postJpaRepository.save(Post.from(addPostRequest, userContext));
    }
}
