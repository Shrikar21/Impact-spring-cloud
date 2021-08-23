package com.training.currencyexchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.currencyexchange.client.CurrencyConversionClient;
import com.training.currencyexchange.model.CurrencyExchange;

@RestController
@RequestMapping("/api/v1")
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyConversionClient ccClient;
		
	@GetMapping("/currency-exchange/{amount}/{from}/to/{to}")
	public ResponseEntity<CurrencyExchange> getCurrencyExchangeAmount(@PathVariable int amount, @PathVariable String from, @PathVariable String to) {
		
		ResponseEntity<Double> response = ccClient.getCurrencyConversionRate(from, to);
		double rate = response.getBody();
		
	    CurrencyExchange exchange = new CurrencyExchange(from, to, amount, amount * rate);
	    return new ResponseEntity<CurrencyExchange>(exchange, HttpStatus.OK);
	}
}


// For the feign client, we have to simply create an interface.