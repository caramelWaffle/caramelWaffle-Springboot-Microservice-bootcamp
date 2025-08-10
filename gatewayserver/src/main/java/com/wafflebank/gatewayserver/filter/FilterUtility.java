package com.wafflebank.gatewayserver.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public String getCorrelationIdHeader(HttpHeaders headers) {
        if (headers.getFirst(CORRELATION_ID_HEADER) != null) {
            return headers.getFirst(CORRELATION_ID_HEADER);
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String headerName, String headerValue) {
        return exchange.mutate()
                .request(request -> request.headers(headers -> headers.set(headerName, headerValue)))
                .build();
    }

    boolean isCorrelationIdPresent(HttpHeaders headers) {
        return headers.containsKey(FilterUtility.CORRELATION_ID_HEADER);
    }

    public String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

}
