package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.users.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PostsService {
    private final PostsRepository postsRepository;

    Post addPost(AddPostRequest addPostRequest, UserContext userContext) {
        return postsRepository.addPost(addPostRequest, userContext);
    }
}
