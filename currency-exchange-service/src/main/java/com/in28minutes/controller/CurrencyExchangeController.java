package com.in28minutes.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.repository.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment;
	@Autowired
	private CurrencyExchangeRepo currencyExchangeRepo;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		String property = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(from, to);
		currencyExchange.setEnvironment(property);
		return currencyExchange; 
	}

}
