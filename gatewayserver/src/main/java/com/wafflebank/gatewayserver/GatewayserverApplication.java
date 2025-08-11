package com.wafflebank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator waffleBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(predicateSpec ->
                        predicateSpec.path("/wafflebank/accounts-service/**")
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec.rewritePath("/wafflebank/accounts-service/(?<segment>.*)", "/${segment}")
                                                .addRequestHeader("X-Request-Time", LocalDateTime.now().toString())
                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                                .circuitBreaker(config -> config.setName("accountsCircuitBreaker").setFallbackUri("forward:/contactSupport"))
                                )
                                .uri("lb://ACCOUNTS")
                )
                .route(predicateSpec ->
                        predicateSpec.path("/wafflebank/loans-service/**")
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec.rewritePath("/wafflebank/loans-service/(?<segment>.*)", "/${segment}")
                                                .addRequestHeader("X-Request-Time", LocalDateTime.now().toString())
                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                                .circuitBreaker(config -> config.setName("laonsCircuitBreaker").setFallbackUri("forward:/contactSupport"))
                                )
                                .uri("lb://LOANS")
                )
                .route(predicateSpec ->
                        predicateSpec.path("/wafflebank/cards-service/**")
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec.rewritePath("/wafflebank/cards-service/(?<segment>.*)", "/${segment}")
                                                .addRequestHeader("X-Request-Time", LocalDateTime.now().toString())
                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                                .circuitBreaker(config -> config.setName("cardsCircuitBreaker").setFallbackUri("forward:/contactSupport"))
                                )
                                .uri("lb://CARD")
                )
                .build();
    }

}
