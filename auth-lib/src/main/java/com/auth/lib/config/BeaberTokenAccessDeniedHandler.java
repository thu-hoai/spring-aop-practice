package com.auth.lib.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class BeaberTokenAccessDeniedHandler implements AccessDeniedHandler {

    private BeaberTokenAccessDeniedHandler deniedHandler = new BeaberTokenAccessDeniedHandler();
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        deniedHandler.handle(request, response, accessDeniedException);
        resolver.resolveException(request, response, null, accessDeniedException);
    }

    public void setRealmName(String realmName) {
        this.deniedHandler.setRealmName(realmName);
    }
}
