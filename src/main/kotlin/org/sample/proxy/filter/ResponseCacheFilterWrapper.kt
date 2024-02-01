package org.sample.proxy.filter

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.filter.factory.cache.ResponseCacheGatewayFilter
import org.springframework.cloud.gateway.filter.factory.cache.ResponseCacheManager
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class ResponseCacheFilterWrapper @Autowired constructor(responseCacheManager: ResponseCacheManager) : GlobalFilter, Ordered {
    val responseFilter: ResponseCacheGatewayFilter = ResponseCacheGatewayFilter(responseCacheManager)
    val log: Log = LogFactory.getLog(javaClass)

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        log.info("Caching Response filter is active")
        return responseFilter.filter(exchange, chain)
    }

    override fun getOrder(): Int =
        responseFilter.order
}