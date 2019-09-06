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

		return repository.save(memory);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Memory> findById(@PathVariable(value = "id") long memoryId) {
		return repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long memoryId) {
		return repository.findById(memoryId).map(record -> {
			repository.deleteById(memoryId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@RequestMapping("/getUserMemories/{id}")
	public Iterable<Memory> getUserMemories(@PathVariable long id) {
		return repository.getUserMemories(id);
	}

	@RequestMapping("story/{story}/findMemoriesByYear/{year}")
	public Iterable<Memory> findMemoriesByYear(@PathVariable long story, @PathVariable int year) {
		return repository.findMemoriesByYear(story, year);
	}

	@RequestMapping("story/{story}/findMemoriesByTag/{tag}")
	public Iterable<Memory> findMemoriesByTag(@PathVariable long story, @PathVariable String tag) {
		return repository.findMemoriesByTag(story, tag);
	}

	@RequestMapping("/findMemoriesByKeyword/{description}")
	public Iterable<Memory> findMemoriesByKeyword(String description) {
		return repository.findMemoriesByKeyword(description);
	}

}
