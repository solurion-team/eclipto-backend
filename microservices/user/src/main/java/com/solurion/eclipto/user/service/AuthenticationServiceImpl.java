package com.solurion.eclipto.user.service;

import com.nimbusds.jose.jwk.JWKSet;
import com.solurion.eclipto.user.dto.JwtAuthenticationResponse;
import com.solurion.eclipto.user.dto.LoginRequest;
import com.solurion.eclipto.user.dto.RegisterRequest;
import com.solurion.eclipto.user.jwt.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JWKSet jwkSet;
    private final JwtGenerator jwtGenerator;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String getJwks() {
        return jwkSet.toPublicJWKSet().toString();
    }

    @Override
    public JwtAuthenticationResponse register(RegisterRequest request) {
        UserDetails userDetails = userService.createUser(request);
        String jwt = jwtGenerator.generate(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse auth(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String jwt = jwtGenerator.generate(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }
}
