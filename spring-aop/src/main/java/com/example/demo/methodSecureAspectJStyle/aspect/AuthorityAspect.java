package com.example.demo.methodSecureAspectJStyle.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

@Aspect
public class AuthorityAspect {
    @Before("@annotation(com.example.demo.methodSecureAspectJStyle.annotation.CustomPreAuthorize)")
    public void handleCustomAuth (final JoinPoint joinPoint) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("Un-authenticate");
        }
        String methodName = joinPoint.getSignature().getName();
        final Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
        if (userAuthorities.stream().noneMatch(
            grantedAuthority -> grantedAuthority.getAuthority().equals(methodName))) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
