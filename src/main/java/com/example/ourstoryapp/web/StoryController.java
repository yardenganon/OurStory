package com.example.ourstoryapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.ourstoryapp.da.StoryRepository;
import com.example.ourstoryapp.domain.Story;


@RestController
public class StoryController {
	
	
	@Autowired
	private StoryRepository repository;

	
    @RequestMapping("/stories/findAll")
    public Iterable<Story> getStories() {
      return repository.findAll();
    }
    
    
    
	@PostMapping("/stories/create")
	public Story create(@Valid @RequestBody Story story) {
		return repository.save(story);
	}

	@GetMapping("/stories/findById/{id}")
	public ResponseEntity<Story> findById(@PathVariable(value = "id") long storyId) {
		return repository.findById(storyId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/stories/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long storyId) {
		return repository.findById(storyId).map(record -> {
			repository.deleteById(storyId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
      
}
