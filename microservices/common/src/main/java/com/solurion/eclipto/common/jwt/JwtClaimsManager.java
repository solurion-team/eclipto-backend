package com.solurion.eclipto.common.jwt;

import org.springframework.security.core.context.SecurityContext;

public interface JwtClaimsManager {
    String USER_ID_CLAIM_KEY = "userId";

    Long extractUserId(SecurityContext securityContext);
    Long extractUserId();
}
