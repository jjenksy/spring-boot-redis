package com.sixthpoint.spring.boot.elasticcache.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@Slf4j
public class UserService {

    private final RestTemplate userRestTemplate;

    public UserService(RestTemplate userRestTemplate) {
        this.userRestTemplate = userRestTemplate;
    }

    @Cacheable(value = "getLongRunningTaskResult", key="{#user}", cacheManager = "cacheManager1Hour")
    public String getLongRunningTaskResult(String user) {
        try {

            ResponseEntity<String> responseEntity = this.userRestTemplate.getForEntity("http://localhost:13085/user", String.class);
            if(responseEntity.hasBody()) return responseEntity.getBody();
        } catch (Exception e) {
            log.error("{}", e);
            return null;
        }
        // throw an exception if it is null
        return null;
    }

    @CacheEvict(value = "getLongRunningTaskResult", key = "#user")
    public void resetUser(String user) {
        // Intentionally blank
    }
}
