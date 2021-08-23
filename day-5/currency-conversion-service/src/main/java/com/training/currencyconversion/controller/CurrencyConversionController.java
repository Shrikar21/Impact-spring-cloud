package com.training.currencyconversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.currencyconversion.repository.CurrencyConversionRepository;

@RestController
@RequestMapping("/api/v1")
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyConversionRepository ccRepository;

	@GetMapping("/currency-conversion/{from}/to/{to}")
	public ResponseEntity<Double> getCurrencyConversionRate(@PathVariable String from, @PathVariable String to) {
		
		double rate = ccRepository.getConversionRate(from, to);
		return new ResponseEntity<Double>(rate, HttpStatus.OK);
	}
}
