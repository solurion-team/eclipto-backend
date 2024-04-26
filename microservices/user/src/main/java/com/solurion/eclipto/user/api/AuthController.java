package com.solurion.eclipto.user.api;

import com.solurion.eclipto.user.dto.JwtAuthenticationResponse;
import com.solurion.eclipto.user.dto.LoginRequest;
import com.solurion.eclipto.user.dto.RegisterRequest;
import com.solurion.eclipto.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<String> getJwks() {
        return ResponseEntity.ok(authenticationService.getJwks());
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.auth(loginRequest));
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> register(RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
}
