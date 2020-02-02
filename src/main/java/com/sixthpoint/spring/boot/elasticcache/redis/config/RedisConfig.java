package com.sixthpoint.spring.boot.elasticcache.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;


@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${spring.redis.prefix}")
    private String redisPrefix;

    @Value("${spring.redis.ttl}")
    private int ttl;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean(name = "cacheManager1Hour")
    public CacheManager cacheManager1Hour(LettuceConnectionFactory redisConnectionFactory) {
        Duration expiration = Duration.ofHours(ttl);
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .prefixKeysWith(redisPrefix)
                        .entryTtl(expiration)).build();
    }

}
