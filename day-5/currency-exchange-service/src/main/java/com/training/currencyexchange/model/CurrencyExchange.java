package com.training.currencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CurrencyExchange {

	private String from;
	private String to;
	private int amount;
	private double finalAmount;
}
