package com.wafflebank.gatewayserver.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Configuration
public class ResponseTraceFilter {
    private static final Logger logger = Logger.getLogger(ResponseTraceFilter.class.getName());

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postResponseTraceFilter() {
        return ((exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            String correlationId = filterUtility.getCorrelationIdHeader(exchange.getRequest().getHeaders());
            exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID_HEADER, correlationId);
            logger.info("Response Trace Filter executed. Correlation ID: " + correlationId);
        })));
    }
}
