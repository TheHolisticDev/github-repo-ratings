package org.example.caching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CacheInvalidator {

    CacheManager cacheManager;

    /**
     * Clear cache every 12 hours
     */
    @Scheduled(fixedRate = 1000*60*60*12)
    public void evictAllCaches() {
        log.info("Invalidating caches");
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

}
