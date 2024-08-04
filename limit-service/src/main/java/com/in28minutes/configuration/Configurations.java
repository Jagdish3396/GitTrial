package com.in28minutes.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
@ConfigurationProperties("limit-service")
public class Configurations {
	private int minimum;
	private int maximum;

}
