package com.solurion.eclipto.user.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.solurion.eclipto.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtGeneratorImpl implements JwtGenerator {
    @Value("${JWK_KID}")
    private String jwkId;
    @Value("${auth.jwt.issuer}")
    private String issuer;
    @Value("${auth.jwt.period.days}")
    private Integer tokenPeriodDays;
    @Value("${auth.jwt.period.hours}")
    private Integer tokenPeriodHours;

    private final JWSSigner jwsSigner;

    @Override
    @SneakyThrows
    public String generate(UserDetails userDetails) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusDays(tokenPeriodDays).plusHours(tokenPeriodHours);

        JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder()
                .subject(userDetails.getUsername())
                .issuer(issuer)
                .issueTime(dateBy(currentTime))
                .expirationTime(dateBy(expirationTime));

        if (userDetails instanceof UserEntity user) {
            claimsSetBuilder.claim("userId", user.getId());
        }

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.EdDSA).keyID(jwkId).build(),
                claimsSetBuilder.build()
        );

        signedJWT.sign(jwsSigner);
        return signedJWT.serialize();
    }

    private Date dateBy(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }
}
