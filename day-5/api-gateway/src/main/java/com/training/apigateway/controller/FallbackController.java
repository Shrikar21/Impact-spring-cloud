package com.training.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
	
	@GetMapping("/movieCatalogService")
	public ResponseEntity<String> movieCatalogServiceFallback() {
		
		return new ResponseEntity<String>("Movie Catalog Service is not up and running", HttpStatus.OK); 
	}
	
	@GetMapping("/currencyExchangeService")
	public ResponseEntity<String> currencyExchangeServiceFallback() {
		
		return new ResponseEntity<String>("Currency Exchange Service is not up and running.", HttpStatus.OK);
	}
		
}
