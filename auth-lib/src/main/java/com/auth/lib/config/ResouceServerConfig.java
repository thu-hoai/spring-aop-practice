package com.auth.lib.config;

import com.auth.lib.model.PublicKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPublicKey;


public abstract class ResouceServerConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.key-uri:#{null}}")
    private String keyUri;
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri:#{null}}")
    private String jwtUri;

    protected JwtDecoder getJwtDecoder(RestTemplate restTemplate) {
        if (StringUtils.isNotBlank(keyUri)) {
            PublicKey key = restTemplate.getForObject(keyUri, PublicKey.class);
            RSAPublicKey publicKey = RsaKeyConverters.x509().convert(
                new ByteArrayInputStream(key.getValue().getBytes())
            );
            return NimbusJwtDecoder.withPublicKey(publicKey).build();
        }
        if (StringUtils.isNotBlank(jwtUri)) {
            return NimbusJwtDecoder.withJwkSetUri(jwtUri).build();
        }
        throw new RuntimeException("Must contain either key-uri in configuration");
    }
}
