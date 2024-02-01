package org.sample.proxy

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.cloud.gateway.filter.factory.cache.ResponseCacheManager
import org.springframework.cloud.gateway.filter.factory.cache.keygenerator.CacheKeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.Duration
import java.util.concurrent.TimeUnit


@Configuration
class CacheConfig {
    @Bean
    fun caffeineConfig(): Caffeine<Any, Any>? =
         Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES)

    @Bean
    @Primary
    fun cacheManager(caffeine: Caffeine<Any, Any>): CacheManager {
        val caffeineCacheManager = CaffeineCacheManager()
        caffeineCacheManager.setCaffeine(caffeine)
        return caffeineCacheManager
    }

    @Bean
    fun cacheName():String = "ALL-CACHES"

    @Bean
    fun responseCacheManager(cacheManager: CacheManager, cacheName: String): ResponseCacheManager =
        ResponseCacheManager(CacheKeyGenerator(), cacheManager.getCache(cacheName), Duration.ofMinutes(30))

}