package com.example.ourstoryapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OurstoryappApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(OurstoryappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OurstoryappApplication.class, args);
		
		
		logger.info("Hello Spring Boot");  
	}

}
