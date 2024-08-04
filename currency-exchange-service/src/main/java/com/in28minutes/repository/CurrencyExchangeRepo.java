package com.in28minutes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.controller.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {
	public CurrencyExchange findByFromAndTo(String from,String to);

}
