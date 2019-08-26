package com.example.ourstoryapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.example.ourstoryapp.da.StoryRepository;
//import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.da.UserRepository;

@SpringBootApplication
public class OurstoryappApplication {
	@Autowired
	private StoryRepository story_repository;
//	@Autowired
//	private MemoryRepository memory_repository;
	@Autowired
	private UserRepository user_repository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(OurstoryappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OurstoryappApplication.class, args);
		
		// test
		logger.info("Hello Spring Boot");  
	}

}
