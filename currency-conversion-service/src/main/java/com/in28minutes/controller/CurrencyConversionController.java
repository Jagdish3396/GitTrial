package com.in28minutes.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.bean.CurrencyConversion;
import com.in28minutes.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CurrencyExchangeProxy feign;
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion caclulateCurrencyConersion(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity ) {
		HashMap<String, String> urivariable=new HashMap<>();
		urivariable.put("from", from);
		urivariable.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversion.class, urivariable);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment()+" rest template");
	}
	
	@GetMapping("/currency-conversion/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion caclulateCurrencyConersionFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity ) {
		CurrencyConversion currencyConversion=feign.retrieveExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment()+" feign-Client");
	}

}
