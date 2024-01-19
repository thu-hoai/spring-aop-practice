package com.auth.lib.service;

import com.auth.lib.model.APIPermission;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AuthCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCacheService.class);
    public static final String PERMISSION_CACHE_KEY = "permissionCache";
    private final CacheManager cacheManager;

    public List<APIPermission> getPermissionCacheByServiceId(String serviceId) {
        List<APIPermission> permissionList = new ArrayList<>();
        Cache cache = cacheManager.getCache(PERMISSION_CACHE_KEY);

        if (cache == null) {
            LOGGER.error("not found cache with key {}", PERMISSION_CACHE_KEY);
        } else if (CollectionUtils.isEmpty(cache.get(serviceId, List.class))) {
            LOGGER.error("not found API permission for service {}", serviceId);
        } else {
            permissionList = cache.get(serviceId, List.class);
        }
        return permissionList;
    }
}
