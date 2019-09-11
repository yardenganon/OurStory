package com.example.ourstoryapp.web;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.da.TagRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.Tag;

@RestController 
@RequestMapping("/tags")
public class TagsController {

	@Autowired
    private TagRepository repository;
	@Autowired
	private LogRepository logRepository;

	final String name = TagsController.class.getName();

	//return all tags	
	@RequestMapping("/findAll")
	public Iterable<Tag> getTags() {
		return repository.findAll();
    } 
	
	//delete tag
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String tag_name) {
		if((repository.findById(tag_name)).isPresent()) {
			return repository.findById(tag_name).map(record -> {
			repository.deleteById(tag_name);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//add tag
	@PostMapping("/create")
	public Tag create(@Valid @RequestBody Tag tag) {
		return repository.save(tag);	
	}
	
	// find tag by name
	@GetMapping("/findById/{id}")
	public ResponseEntity<Tag> findByid( @PathVariable(value = "id") String tag_name) {
		if((repository.findById(tag_name)).isPresent()) {
			return repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping("/findTop3TagsByStoryId")
	public Iterable<String> findTop3TagsByStoryId(@RequestParam("storyId") long storyId) {
		return repository.findTop3TagsByStoryId(storyId);
	}
	
	
	@RequestMapping("/findTop5TagsByStoryId")
	public Iterable<String> findTop5TagsByStoryId(@RequestParam("storyId") long storyId) {
		return repository.findTop5TagsByStoryId(storyId);
	}
	
	@RequestMapping("/findTop10TagsByStoryId")
	public Iterable<String> findTop10TagsByStoryId(@RequestParam("storyId") long storyId) {
		return repository.findTop10TagsByStoryId(storyId);
	}
}
