package com.in28minutes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
public class CircuitBreakerController {
Logger logger=	LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
//	@Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")
//	@Retry(name = "default")
//	@CircuitBreaker(name="default",fallbackMethod = "hardCodedResponse")
//	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public String sampleapi() {
		logger.info("call is gone to api");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class);
//		return forEntity.getBody();
		return "sample-api";
	}
	public String hardCodedResponse(Exception e) {
		return "FallBack-Response";
	}

}
