package com.exam.examserver.configuration.cacheconfig;



import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;
import java.util.List;

@Configuration
@EnableCaching
public class EhcacheConfig {

    @Bean
    public CacheManager ehcaheManager() {
        System.out.println("helooooooooooooooooooooooo");
        CacheConfiguration<String, List> cachecConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class,
                        List.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
                .build();

        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
//        javax.cache.configuration.Configuration<String, List> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cachecConfig);

        javax.cache.configuration.Configuration<String, List> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cachecConfig);
        cacheManager.createCache("cachestore", configuration);
        return cacheManager;
    }
}
