package com.training.currencyexchange.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-conversion-service")
public interface CurrencyConversionClient {
	
	@GetMapping("/api/v1/currency-conversion/{from}/to/{to}")
	public ResponseEntity<Double> getCurrencyConversionRate(@PathVariable String from, @PathVariable String to);
}
