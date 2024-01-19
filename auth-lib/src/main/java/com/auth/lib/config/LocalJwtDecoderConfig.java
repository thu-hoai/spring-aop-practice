package com.auth.lib.config;

import ch.qos.logback.core.net.ssl.SSLContextFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
@Profile("local")
public class LocalJwtDecoderConfig extends ResouceServerConfig {

    @Bean
    public JwtDecoder localJwtDecoder() {
        return getJwtDecoder(localRestTemplate());
    }

    private RestTemplate localRestTemplate() {
        final SSLContext sslContext;

        // bypass by adding trustore keystore
        return null;
    }
}