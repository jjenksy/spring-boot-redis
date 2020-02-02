package com.sixthpoint.spring.boot.elasticcache.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@Slf4j
public class UserService {

    @Cacheable(value = "getLongRunningTaskResult", key="{#user}", cacheManager = "cacheManager1Hour")
    public String getLongRunningTaskResult(String user) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForEntity("http://localhost:13085/user", String.class).getBody();
        } catch (Exception e) {
            log.error("{}", e);
            return null;
        }
    }

    @CacheEvict(value = "getLongRunningTaskResult", key = "#user")
    public void resetUser(String user) {
        // Intentionally blank
    }
}
