package com.share.memories.application.users;

import com.share.memories.infrastructure.rest.ApiErrorCode;
import com.share.memories.infrastructure.rest.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(s)
                                .map(user -> new User(user.getEmail(), user.getPassword(), Collections.emptyList()))
                                .orElseThrow(() -> new AppException(ApiErrorCode.USER_NOT_EXISTS, "User not exists", HttpStatus.BAD_REQUEST));
    }
}
