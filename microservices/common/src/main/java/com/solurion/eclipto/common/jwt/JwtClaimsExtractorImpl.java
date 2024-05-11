package com.solurion.eclipto.common.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtClaimsExtractorImpl implements JwtClaimsExtractor {
    @Override
    public Long extractUserId(String token) {
        return (Long) extractClaims(token).get("userId");
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().parseClaimsJwt(token).getBody();
    }
}
