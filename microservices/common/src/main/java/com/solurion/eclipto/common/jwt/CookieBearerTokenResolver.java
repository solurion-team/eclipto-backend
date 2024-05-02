package com.solurion.eclipto.common.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Primary
@Component
@RequiredArgsConstructor
public class CookieBearerTokenResolver implements BearerTokenResolver {
    private final DefaultBearerTokenResolver defaultBearerTokenResolver;

    @Override
    public String resolve(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "access_token");
        if (cookie != null)
            return cookie.getValue();
        else
            return defaultBearerTokenResolver.resolve(request);
    }
}
