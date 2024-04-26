package com.solurion.eclipto.user.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.Ed25519Signer;
import com.nimbusds.jose.crypto.Ed25519Verifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class JwkConfig {
    @Value("${JWK_KID}")
    private String jwkId;

    @Bean
    @SneakyThrows
    public JWKSet jwkSet() {
        ClassPathResource res = new ClassPathResource("jwks.json");
        return JWKSet.load(res.getInputStream());
    }

    @Bean
    @SneakyThrows
    public JWKSource<SecurityContext> jwkSource(JWKSet jwkSet) {
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    @SneakyThrows
    public JWSSigner jwsSigner(JWKSet jwkSet) {
        JWK jwk = jwkSet.getKeyByKeyId(jwkId);

        return new Ed25519Signer(jwk.toOctetKeyPair());
    }
}
