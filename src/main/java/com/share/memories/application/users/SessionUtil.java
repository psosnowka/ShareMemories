package com.share.memories.application.users;

import com.share.memories.application.users.dto.LoginUserRequest;
import com.share.memories.application.util.SecurityUtil;
import com.share.memories.application.util.dto.TokenResponse;
import com.share.memories.infrastructure.rest.ApiErrorCode;
import com.share.memories.infrastructure.rest.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionUtil {
    private final UserApplicationService userApplicationService;
    private final SecurityUtil securityUtil;

    public TokenResponse getSessionToken(LoginUserRequest loginUserRequest) {
        AppUser user = userApplicationService.getUserByCredentials(loginUserRequest);
        return securityUtil.createSessionToken(user.getEmail());
    }

    public UserContext getUserContext() {
        return getLoggedClient()
                .map(userApplicationService::getUserByEmail)
                .map(AppUser::getContext)
                .orElseThrow(() -> new AppException(ApiErrorCode.INVALID_TOKEN, "User from token not exists", HttpStatus.BAD_REQUEST));
    }

    private Optional<String> getLoggedClient() {

        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        String principal = (String) authentication.getPrincipal();
        return Optional.ofNullable(principal);
    }
}
