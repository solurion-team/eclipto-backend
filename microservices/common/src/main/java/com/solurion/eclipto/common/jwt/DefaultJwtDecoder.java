package com.solurion.eclipto.common.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class DefaultJwtDecoder implements JwtDecoder {
    private final JWSVerifier jwsVerifier;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            SignedJWT signedJwt = SignedJWT.parse(token);
            if (!signedJwt.verify(jwsVerifier)) {
                throw new JOSEException("Failed to verify JWT signature");
            }

            JWTClaimsSet claimsSet = signedJwt.getJWTClaimsSet();
            Map<String, Object> mappedClaims = new HashMap<>();
            for (String key : claimsSet.getClaims().keySet()) {
                Object value = claimsSet.getClaim(key);
                if (key.equals("exp") || key.equals("iat")) {
                    value =  claimsSet.getDateClaim(key).toInstant();
                }
                mappedClaims.put(key, value);
            }
            return Jwt.withTokenValue(token)
                    .headers((m) -> m.putAll(signedJwt.getHeader().toJSONObject()))
                    .claims((m) -> m.putAll(mappedClaims))
                    .build();
        } catch (JOSEException | ParseException ex) {
            throw new RuntimeException("Failed to decode JWT: " + ex.getMessage(), ex);
        }
    }
}
