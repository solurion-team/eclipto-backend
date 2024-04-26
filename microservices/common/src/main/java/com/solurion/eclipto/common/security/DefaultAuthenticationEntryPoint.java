package com.solurion.eclipto.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solurion.eclipto.common.exception.ErrorDtoFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@RequiredArgsConstructor
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setHeader("WWW-Authenticate", "Bearer");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        var errorDto = ErrorDtoFactory.initErrorDto(HttpStatus.UNAUTHORIZED, request);
        response.getOutputStream().println(objectMapper.writeValueAsString(errorDto));
    }
}
