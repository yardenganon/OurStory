package com.example.ourstoryapp.web;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.domain.Memory;

@RestController
@RequestMapping("/memories")
public class MemoryController {

	@Autowired
	MemoryRepository repository;
	Logger logger = LogManager.getLogger(MemoryController.class);

	@GetMapping("/findAll")
	public Iterable<Memory> findAll() {
		logger.info("Find All Memories");
		return repository.findAll();
	}

	@PostMapping("/create")
	public Memory create(@Valid @RequestBody Memory memory) {
		logger.info("Create Memory");
		return repository.save(memory);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Memory> findById(@PathVariable(value = "id") long memoryId) {
		if(repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logger.info("Find Memory By ID");
			return repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		else {
			logger.info("Memory By ID is not Existing");
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long memoryId) {
		if(repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logger.info("Successfully Deleted Memory");
			return repository.findById(memoryId).map(record -> {
				repository.deleteById(memoryId);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		}
		else {
			logger.info("Memory is not existing");
			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping("/getUserMemories/{id}")
	public Iterable<Memory> getUserMemories(@PathVariable long id) {
		if(repository.getUserMemories(id)!= null) {
			logger.info("Get User's Memories");
			return repository.getUserMemories(id);
		}
		else {
			logger.info("User's Memories Are Not Found");
			return repository.getUserMemories(id);
		}
	}

	@RequestMapping("story/{story}/findMemoriesByYear/{year}")
	public Iterable<Memory> findMemoriesByYear(@PathVariable long story, @PathVariable int year) {
		/*
		if(repository.findMemoriesByYear(story, year)!=null) {
			logger.info("Find Memories By Year");
			return repository.findMemoriesByYear(story, year);

		}
		else {
			logger.info("There Aren't Memories By Year");
			return repository.findMemoriesByYear(story, year);
		}
		*/
		return repository.findMemoriesByYear(story, year);

	}

	@RequestMapping("story/{story}/findMemoriesByTag/{tag}")
	public Iterable<Memory> findMemoriesByTag(@PathVariable long story, @PathVariable String tag) {
		/*
		 if(repository.findMemoriesByTag(story, tag) != null) {
			logger.info("Find Memories By Tag");
			return repository.findMemoriesByTag(story, tag);
		}
		else {
			logger.info("There Aren't Memories By Tag");
			return repository.findMemoriesByTag(story, tag);
		}
		*/
		return repository.findMemoriesByTag(story, tag);
	}

	@RequestMapping("/findMemoriesByKeyword/{description}")
	public Iterable<Memory> findMemoriesByKeyword(String description) {
		/*
		if(repository.findMemoriesByKeyword(description) != null) {
			logger.info("Find Memories By Keyword");
			return repository.findMemoriesByKeyword(description);
		}
		else {
			logger.info("There Aren't Memories By Keyword");
			return repository.findMemoriesByKeyword(description);
		}
		*/
		return repository.findMemoriesByKeyword(description);

	}
}
