package com.share.memories.application.posts;

import com.share.memories.application.posts.dto.AddUserRequest;
import com.share.memories.application.posts.dto.AppUserContext;
import com.share.memories.application.posts.dto.LoginUserRequest;
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

    AppUserContext addUser(AddUserRequest addUserRequest) {
        if (appUserRepository.findByEmail(addUserRequest.getEmail()).isPresent()) {
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = AppUser.from(addUserRequest, passwordEncoder);
        AppUser savedUser = appUserRepository.save(appUser);
        log.info("Created user: {}", savedUser);
        return savedUser.getContext();
    }

    AppUserContext getUserByCredentials(LoginUserRequest loginUserRequest) {
        return appUserRepository.findByEmail(loginUserRequest.getEmail())
                                .filter(user -> isPasswordMatches(loginUserRequest.getPassword(), user.getPassword()))
                                .map(AppUser::getContext)
                                .orElseThrow(() -> new AppException(ApiErrorCode.USER_NOT_EXISTS, "User not exists", HttpStatus.BAD_REQUEST));
    }

    private boolean isPasswordMatches(String requestPassword, String password) {
        return passwordEncoder.matches(requestPassword, password);
    }

}
