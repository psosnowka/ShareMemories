package com.share.memories.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenGenerator {
    public static String generate(Payload payload, String secretKey) {

        Date date = new Date(System.currentTimeMillis() + payload.expirationTime);
        return Jwts.builder()
                   .setSubject(payload.username)
                   .setExpiration(date)
                   .signWith(SignatureAlgorithm.HS512, secretKey)
                   .compact();
    }

    public static Claims validateToken(String token, String secret) {
        if (token != null) {
            return Jwts.parser()
                       .setSigningKey(secret)
                       .parseClaimsJws(token)
                       .getBody();
        }
        return null;
    }

    @Value(staticConstructor = "of")
    @Wither
    public static class Payload {
        private String username;
        private long expirationTime;
    }
}
