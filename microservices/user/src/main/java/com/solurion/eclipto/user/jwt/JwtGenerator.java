package com.solurion.eclipto.user.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtGenerator {
    String generate(UserDetails userDetails);
}
