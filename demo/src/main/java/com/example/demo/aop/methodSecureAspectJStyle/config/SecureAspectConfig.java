package com.example.demo.aop.methodSecureAspectJStyle.config;

import com.example.demo.aop.methodSecureAspectJStyle.aspect.AuthorityAspect;
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
