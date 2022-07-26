package com.example.demo.aop.methodSecureAspectJStyle.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SecureAspectConfig {

    @Bean
    public AuthorityAspect aspectConfig() {
        return new AuthorityAspect();
    }
}
