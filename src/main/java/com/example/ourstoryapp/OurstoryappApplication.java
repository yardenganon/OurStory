package com.example.ourstoryapp;


import java.net.URI;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ourstoryapp.da.CommentRepository;
import com.example.ourstoryapp.da.LikesRepository;
import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.da.StoryRepository;
import com.example.ourstoryapp.da.UserRepository;
import com.example.ourstoryapp.domain.Likes;
import com.example.ourstoryapp.domain.Memory;
import com.example.ourstoryapp.domain.Story;
import com.example.ourstoryapp.domain.User;

@SpringBootApplication
public class OurstoryappApplication {
	@Autowired
	private StoryRepository story_repository;
	@Autowired
	private MemoryRepository memory_repository;
	@Autowired
	private UserRepository user_repository;
	@Autowired
	private CommentRepository comment_repository;
	@Autowired
	private LikesRepository likes_repository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(OurstoryappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OurstoryappApplication.class, args);
		
		// test
		logger.info("Hello Spring Boot");  
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Place your code here
			
			User u = new User();
			Memory m1 = new Memory();
			Likes l = new Likes(m1,u);
			Story s = new Story();
			
			story_repository.save(s);
			user_repository.save(u);
			memory_repository.save(m1);
			likes_repository.save(l);

			


		};
	}


}
