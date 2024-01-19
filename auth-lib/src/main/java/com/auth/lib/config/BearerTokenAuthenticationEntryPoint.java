package com.auth.lib.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

public class BearerTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private BearerTokenAuthenticationEntryPoint defaultEntryPoint = new BearerTokenAuthenticationEntryPoint();

    @Autowired
    @Qualifier("handleExceptionResolver")
    private HandlerExceptionResolver resolver;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        defaultEntryPoint.commence(request, response, authException);

        resolver.resolveException(
            request, response, null, new RuntimeException("")
        );
    }
}
