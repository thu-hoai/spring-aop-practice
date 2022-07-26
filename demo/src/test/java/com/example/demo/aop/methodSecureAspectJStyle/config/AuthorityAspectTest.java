package com.example.demo.aop.methodSecureAspectJStyle.config;

import com.example.demo.aop.methodSecureAspectJStyle.annotation.CustomPreAuthorize;
import com.example.demo.aop.methodSecureAspectJStyle.aspect.AuthorityAspect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    SecureAspectConfig.class, AuthorityAspect.class, DummyServiceTest.class
})
class AuthorityAspectTest {

    @Autowired DummyServiceTest mockServiceTest;

    @Mock private SecurityContext securityContext;

    @Test
    void givenMethodWithNonPreAuthorizeAnnotation_whenIntercept_thenNothingHappen() {
        Assertions.assertDoesNotThrow(() -> mockServiceTest.methodWithoutAnnotation());
    }

    @Test
    void givenMethodWithPreAuthorizeAnnotation_whenIntercept_thenThrowException() {
        setupSecurityContext(List.of(new SimpleGrantedAuthority("any")), securityContext);
        Assertions.assertThrows(RuntimeException.class,
            () -> mockServiceTest.methodTest());
    }

    @Test
    void givenValidGrantedAuthority_whenIntercept_thenByPass() {
        setupSecurityContext(List.of(new SimpleGrantedAuthority("methodTest")), securityContext);
        Assertions.assertDoesNotThrow(() -> mockServiceTest.methodTest());
    }

    private static void setupSecurityContext(
        Collection<GrantedAuthority> authorities, SecurityContext securityContext
        ) {
        Jwt jwt = Jwt.withTokenValue("token").header("alg", "none")
            .claim("ISS", "")
            .build();
        Mockito.when(securityContext.getAuthentication()).thenReturn(
            new JwtAuthenticationToken(jwt, authorities)
        );
        SecurityContextHolder.setContext(securityContext);
    }
}

@Component
class DummyServiceTest {
    void methodWithoutAnnotation () {}

    @CustomPreAuthorize
    void methodTest() {}
}
