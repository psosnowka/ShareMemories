package com.share.memories.application.users;

import com.share.memories.application.base.BaseEntity;
import com.share.memories.application.posts.PostsFacade;
import com.share.memories.application.posts.dto.PostResponse;
import com.share.memories.application.users.dto.AddUserRequest;
import com.share.memories.application.users.dto.AddUserResponse;
import com.share.memories.application.util.SecurityUtil;
import com.share.memories.application.util.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UsersService {
    private final UserApplicationService userApplicationService;
    private final SecurityUtil securityUtil;
    private final PostsFacade postsFacade;

    AddUserResponse addUserAndGetSessionToken(AddUserRequest addUserRequest) {
        AppUser appUser = userApplicationService.addUser(addUserRequest);
        TokenResponse sessionToken = securityUtil.createSessionToken(appUser.getEmail());
        return AddUserResponse.of(appUser.getContext(), sessionToken);
    }

    Mono<AppUser> addFolower(UserContext userContext, String userUuid) {
        return Mono.fromCallable(() -> userApplicationService.addFollower(userContext, userUuid));
    }

    AppUser getUser(UserContext userContext) {
        return userApplicationService.getUserByEmail(userContext.getEmail());
    }

    List<PostResponse> getAllPostsCreatedByUser(String uuid) {
        AppUser userByUuid = userApplicationService.getUserByUuid(uuid);
        return postsFacade.getAllPostsForUsers(Collections.singletonList(userByUuid.getUuid()));
    }

    List<PostResponse> getAllPostsForUser(UserContext userContext) {
        List<String> userUuids = userApplicationService.getUserByUuid(userContext.getUuid())
                                                       .getFollowers()
                                                       .stream()
                                                       .map(BaseEntity::getUuid)
                                                       .collect(Collectors.toList());
        userUuids.add(userContext.getUuid());
        return postsFacade.getAllPostsForUsers(userUuids);

    }

}
