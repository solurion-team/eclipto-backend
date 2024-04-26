package com.solurion.eclipto.common.config;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.Ed25519Verifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.solurion.eclipto.common.jwt.DefaultJwtDecoder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.net.URL;

@Configuration
public class JwtConfig {
    @Value("${JWK_KID}")
    private String jwkId;
    @Value("${JWKS_URL}")
    private String jwksUrl;

    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean(JWKSet.class)
    public JWKSet jwkSet() {
        return JWKSet.load(new URL(jwksUrl));
    }

    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean(JWSVerifier.class)
    public JWSVerifier jwsVerifier(JWKSet jwkSet) {
        JWK jwk = jwkSet.getKeyByKeyId(jwkId);

        return new Ed25519Verifier(jwk.toOctetKeyPair().toPublicJWK());
    }

    @Bean
    @SneakyThrows
    public JwtDecoder jwtDecoder(JWSVerifier jwsVerifier) {
        return new DefaultJwtDecoder(jwsVerifier);
    }
}
