package com.training.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.training.apigateway.config.filter.MyLoggingFilter;


@Configuration
public class CloudGatewayConfig {
	
	@Autowired
	private MyLoggingFilter loggingFilter;

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		
		return builder.routes()
				             .route(r -> r.path("/api/v1/catalog/**")
				            		          .filters(f -> f.addRequestHeader("secret-key", "mysecret")
				            		        		             .addResponseHeader("cookie", "abcd123")
				            		        		             .filter(loggingFilter)
				            		        		             .hystrix(c -> c.setName("circuit1")
				            		        		            		              .setFallbackUri("forward://fallback/movieCatalogService")))
				            		           .uri("lb://MOVIE-CATALOG-SERVICE")
				            		           .id("movie-catalog-service"))
				             .route(r -> r.path("/api/v1/currency-exchange/**")
				            		           .filters(f -> f.addRequestHeader("secret-key", "mysecret")
				            		        		              .addResponseHeader("cookie", "abcd123"))
				            		          .uri("lb://CURRENCY-EXCHANGE-SERVICE")
				            		          .id("currency-exchange-service"))
				             .build();
	}
}
