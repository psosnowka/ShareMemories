package com.share.memories.application.users;

import com.share.memories.application.posts.dto.PostResponse;
import com.share.memories.application.users.dto.AddUserRequest;
import com.share.memories.application.users.dto.AddUserResponse;
import com.share.memories.application.users.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersFacade {
    private final UsersService usersService;

    public AddUserResponse addUserAndCreateSessionToken(AddUserRequest addUserRequest) {
        return usersService.addUserAndGetSessionToken(addUserRequest);
    }

    public Mono<UserResponse> addFollower(UserContext userContext, String userUuid) {
        return usersService.addFolower(userContext, userUuid)
                           .map(AppUser::getUserResponse);
    }

    public List<PostResponse> getAllPostsForUser(UserContext userContext) {
        return usersService.getAllPostsForUser(userContext);
    }

    public UserResponse getUser(UserContext userContext) {
        return usersService.getUser(userContext)
                           .getUserResponse();
    }

    public List<PostResponse> getAllPostsCreatedByUser(String uuid) {
        return usersService.getAllPostsCreatedByUser(uuid);
    }

}
