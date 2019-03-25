package com.share.memories.infrastructure.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtTokenAuthenticationFilter extends BasicAuthenticationFilter {
    private SecurityConstants securityConstants;

    JwtTokenAuthenticationFilter(AuthenticationManager authenticationManager, SecurityConstants securityConstants) {
        super(authenticationManager);
        this.securityConstants = securityConstants;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(securityConstants.getHeader());
        if (header == null || !header.startsWith(securityConstants.getPrefix())) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(securityConstants.getHeader());
        if (token != null) {
            String pureToken = token.replace(securityConstants.getPrefix(), "");
            Claims claims = TokenGenerator.validateToken(pureToken, securityConstants.getSecret());

//            String user = Jwts.parser()
//                              .setSigningKey(SECRET)
//                              .parseClaimsJws(pureToken)
//                              .getBody()
//                              .getSubject();
            String user = claims.getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
