package com.solurion.eclipto.common.jwt;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtClaimsManagerImpl implements JwtClaimsManager {
    @Override
    public Long extractUserId(SecurityContext securityContext) {
        return extractClaim(securityContext, USER_ID_CLAIM_KEY);
    }

    @Override
    public Long extractUserId() {
        return extractUserId(SecurityContextHolder.getContext());
    }

    private <T> T extractClaim(SecurityContext securityContext, String claimName) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) securityContext.getAuthentication();
        return token.getToken().getClaim(claimName);
    }
}
