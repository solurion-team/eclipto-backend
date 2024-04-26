package com.solurion.eclipto.user.service;

import com.solurion.eclipto.user.dto.JwtAuthenticationResponse;
import com.solurion.eclipto.user.dto.LoginRequest;
import com.solurion.eclipto.user.dto.RegisterRequest;

public interface AuthenticationService {
    String getJwks();

    JwtAuthenticationResponse register(RegisterRequest request);

    JwtAuthenticationResponse auth(LoginRequest request);
}
