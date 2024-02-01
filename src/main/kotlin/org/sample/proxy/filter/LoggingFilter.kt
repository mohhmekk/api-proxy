package org.sample.proxy.filter

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI

@Component
class LoggingFilter: GlobalFilter{
    private var log: Log = LogFactory.getLog(javaClass)

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        val uris: Set<URI> = exchange!!.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, emptySet())
        val originalUri = if (uris.isEmpty()) "Unknown" else uris.iterator().next().toString()
        val route: Route? = exchange.getAttribute(GATEWAY_ROUTE_ATTR)
        val routeUri: URI? = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)
        log.info(
            ("Incoming request " + originalUri + " is routed to id: " + route?.id
                    ).toString() + ", uri:" + routeUri
        )
        return chain!!.filter(exchange)
    }
}