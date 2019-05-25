package com.share.memories.application.users;

import com.share.memories.application.users.dto.AddUserRequest;
import com.share.memories.application.users.dto.LoginUserRequest;
import com.share.memories.infrastructure.rest.ApiErrorCode;
import com.share.memories.infrastructure.rest.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class UserApplicationService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    AppUser addUser(AddUserRequest addUserRequest) {
        if (appUserRepository.findByEmail(addUserRequest.getEmail()).isPresent()) {
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = AppUser.from(addUserRequest, passwordEncoder);
        AppUser savedUser = appUserRepository.save(appUser);
        log.info("Created user: {}", savedUser);
        return savedUser;
    }

    AppUser getUserByEmail(String email) {
        return appUserRepository.findByEmail(email)
                                .orElseThrow(() -> new AppException(ApiErrorCode.USER_NOT_EXISTS, "User not exists", HttpStatus.BAD_REQUEST));
    }

    AppUser getUserByCredentials(LoginUserRequest loginUserRequest) {
        return appUserRepository.findByEmail(loginUserRequest.getEmail())
                                .filter(user -> isPasswordMatches(loginUserRequest.getPassword(), user.getPassword()))
                                .orElseThrow(() -> new AppException(ApiErrorCode.USER_NOT_EXISTS, "User not exists", HttpStatus.BAD_REQUEST));
    }

    AppUser addFollower(UserContext userContext, String userUuid) {
        AppUser follow = appUserRepository.findAppUserByUuid(userUuid)
                                          .orElseThrow(
                                                  () -> new AppException(ApiErrorCode.USER_NOT_EXISTS, "User not exisits", HttpStatus.BAD_REQUEST));
        AppUser user = appUserRepository.findAppUserByUuid(userContext.getUuid())
                                        .map(appUser -> appUser.addFollower(follow))
                                        .orElseThrow(() -> new IllegalStateException("Invalid system state"));
        return appUserRepository.save(user);
    }

    AppUser getUserByUuid(String uuid) {
        return appUserRepository.findAppUserByUuid(uuid)
                                .orElseThrow(() -> new AppException(ApiErrorCode.USER_NOT_EXISTS, "User not found", HttpStatus.BAD_REQUEST));
    }

    private boolean isPasswordMatches(String requestPassword, String password) {
        return passwordEncoder.matches(requestPassword, password);
    }

}
