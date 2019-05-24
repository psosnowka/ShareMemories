package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.posts.dto.UpdatePostRequest;
import com.share.memories.application.users.UserContext;
import com.share.memories.infrastructure.rest.ApiErrorCode;
import com.share.memories.infrastructure.rest.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class PostsService {
    private final PostJpaRepository postJpaRepository;

    Post addPost(AddPostRequest addPostRequest, UserContext userContext) {
        return postJpaRepository.save(Post.from(addPostRequest, userContext));
    }

    Post updatePost(UpdatePostRequest request, UserContext userContext) {
        Post updatedPost = postJpaRepository.findFirstByUuid(request.getPostUuid())
                                            .filter(post -> post.getOwnerUuid().equals(userContext.getUuid()))
                                            .map(post -> post.updatePost(request))
                                            .orElseThrow(
                                                    () -> new AppException(ApiErrorCode.POST_NOT_EXISTS, "Cant find posts for this user",
                                                                           HttpStatus.BAD_REQUEST));
        log.info("Update post:{}", updatedPost);
        return postJpaRepository.save(updatedPost);
    }

    List<Post> getAllPostsForUsers() {
        return postJpaRepository.findAll();
    }

    Post getPostByUuid(String uuid) {
        return postJpaRepository.getPostByUuid(uuid)
                                .orElseThrow(() -> new AppException(ApiErrorCode.CANT_FIND_POST, "Cant find post", HttpStatus.BAD_REQUEST));
    }
}
