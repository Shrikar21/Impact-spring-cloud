package com.training.apigateway.config.filter;

import java.util.logging.Logger;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MyLoggingFilter implements GatewayFilter {

	private static Logger logger = Logger.getLogger(MyLoggingFilter.class.getName());
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Request PATH => " + exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
