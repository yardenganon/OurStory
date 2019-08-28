package com.example.ourstoryapp;


import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.example.ourstoryapp.domain.Comment;
import com.example.ourstoryapp.domain.Likes;
import com.example.ourstoryapp.domain.Memory;
import com.example.ourstoryapp.domain.Tag;
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
			
			
			@SuppressWarnings("deprecation")
			Date d1 = new Date(1992,1,1);
			@SuppressWarnings("deprecation")
			Date d2 = new Date(2019,26,8);
			//Memory m1 = new Memory(1,"Memory one is the first picture I have of him",d1,null);
			//Comment c1 = new Comment(m1,d2,"I don't know him but, Rest in peace");
			
			
//			memory_repository.save(m1);
//			comment_repository.save(c1);
			
			
			//Like l2 = new Like(new User());

			//setlikes.add(l2);

//			Memory m1 = new Memory(1,"sakdjkalsdj",d1);
//			memory_repository.save(m1);
//			Like l1 = new Like(true,new User());
//			Set<Like> setlikes = new HashSet<>();
//			setlikes.add(l1);
//			m1.setLikes(setlikes);
	
//			like_repository.save(l1);
			
			User u = new User();
			Memory m1 = new Memory();
			Likes l = new Likes(m1,u);

			user_repository.save(u);
			memory_repository.save(m1);
			
			likes_repository.save(l);

			


		};
	}


}
