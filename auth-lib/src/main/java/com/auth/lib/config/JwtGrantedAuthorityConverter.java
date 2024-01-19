package com.auth.lib.config;

import com.auth.lib.model.APIPermission;
import com.auth.lib.service.AuthCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtGrantedAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Value("${spring.application.name}")
    private String serviceId;

    private final AuthCacheService service;

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        String clientId = jwt.getClaim("client_id");
        List<APIPermission> permissions = service.getPermissionCacheByServiceId(serviceId);
        if (!CollectionUtils.isEmpty(permissions)) {
            List<String> access = permissions.stream().filter(
                apiPermission -> apiPermission.getServiceId().equals(serviceId)
                && apiPermission.getClientId().equals(clientId)
            ).map(APIPermission::getApiPattern)
                .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(access)) {
                return access.stream().map(
                    SimpleGrantedAuthority::new
                ).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
