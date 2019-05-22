package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.users.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class PostsService {
    private final PostJpaRepository postJpaRepository;

    Post addPost(AddPostRequest addPostRequest, UserContext userContext) {
        return postJpaRepository.save(Post.from(addPostRequest, userContext));
    }

    List<Post> getAllPostsForUsers() {
        return postJpaRepository.findAll();
    }

}
