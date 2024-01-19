package com.auth.lib.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableCaching
@Import(JwtGrantedAuthorityConverter.class)
public class AuthConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(
        JwtGrantedAuthorityConverter jwtGrantedAuthorityConverter
    ) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthorityConverter);
        return jwtAuthenticationConverter;
    }
}
