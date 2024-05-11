package com.solurion.eclipto.common.jwt;

public interface JwtClaimsExtractor {
    Long extractUserId(String token);
}
