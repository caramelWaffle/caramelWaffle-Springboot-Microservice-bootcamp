package com.wafflebank.gatewayserver.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {
    
    private static final Logger logger = Logger.getLogger(RequestTraceFilter.class.getName());
    
    @Autowired
    FilterUtility filterUtility;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (filterUtility.isCorrelationIdPresent(requestHeaders)) {
            logger.info("Correlation ID found in request headers: " + requestHeaders.getFirst(FilterUtility.CORRELATION_ID_HEADER));
        } else {
            String correlationId = filterUtility.generateCorrelationId();
            exchange = filterUtility.setRequestHeader(exchange, FilterUtility.CORRELATION_ID_HEADER, correlationId);
            logger.info("Correlation ID generated and added to request headers: " + correlationId);
        }
        return chain.filter(exchange);
    }
}
