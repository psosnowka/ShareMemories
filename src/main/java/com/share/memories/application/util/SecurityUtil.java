package com.share.memories.application.util;

import com.share.memories.application.util.dto.TokenResponse;
import com.share.memories.infrastructure.security.SecurityConstants;
import com.share.memories.infrastructure.security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityUtil {
    private final SecurityConstants securityConstants;

    public TokenResponse createSessionToken(String username) {
        String token = TokenGenerator.generate(TokenGenerator.Payload.of(username, securityConstants.getExpiration()), securityConstants.getSecret());
        return new TokenResponse(token);
    }
}
