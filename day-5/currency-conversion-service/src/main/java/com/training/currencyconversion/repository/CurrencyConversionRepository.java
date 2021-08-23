package com.training.currencyconversion.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.currencyconversion.model.CurrencyConversion;

@Repository
public class CurrencyConversionRepository {
	
	private List<CurrencyConversion> currencyConversions;
	
	public CurrencyConversionRepository() {
		
		currencyConversions = Arrays.asList(new CurrencyConversion("USD", "INR", 74.43),
				                                            new CurrencyConversion("USD", "EUR", 0.80),
				                                            new CurrencyConversion("EUR", "INR", 88.36));
	}
		
	public double getConversionRate(String from, String to) {
		
		for (CurrencyConversion cc: currencyConversions) {
			if (cc.getFrom().equals(from) && cc.getTo().equals(to)) 
				return cc.getAmount();
		}
		
		return 0.0;
	}
}	
