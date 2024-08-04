package com.in28minutes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.bean.Limits;
import com.in28minutes.configuration.Configurations;

@RestController
public class LimitController {
	@Autowired
	private Configurations configurations;
	
	@GetMapping("/limits")
	public Limits getlimits() {
//		return new Limits(1,1000);
		return new Limits(configurations.getMinimum(),configurations.getMaximum());
	}

}
