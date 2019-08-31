package com.example.ourstoryapp.web;

import javax.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.ourstoryapp.da.StoryRepository;
import com.example.ourstoryapp.domain.Story;


@RestController
public class StoryController {
	
	StoryRepository repository; 
	
	StoryController(StoryRepository sc) // best practice- don't use @Autowired here
	{
		this.repository = sc;
	}
	
	// Basic CRUD
	
	// get all stories - sorted by ID (Read)
    @GetMapping("/stories/findAll")
    public Iterable<Story> getStories() {
      return repository.findAll();
    }
    
    // create new instance of Story (Create)
	@PostMapping("/stories/create")
	public Story create(@Valid @RequestBody Story story) {
		return repository.save(story);
	}

	// get story by ID (Read)
	@GetMapping("/stories/findById/{id}")
	public ResponseEntity<Story> findById(@PathVariable(value = "story_id") long storyId) {
		return repository.findById(storyId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// delete story by ID (Delete)
	@DeleteMapping("/stories/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("story_id") long storyId) {
		return repository.findById(storyId).map(record -> {
			repository.deleteById(storyId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	// update story by id using new values (Update)
	@PutMapping(value="/{id}")
	  public ResponseEntity<Story> update(@PathVariable("story_id") long storyId,
	                                        @RequestBody Story story){
	    return repository.findById(storyId)
	        .map(record -> {
	            record.setDate_of_birth(story.getDate_of_birth());
	            record.setDate_of_death(story.getDate_of_death());
	            record.setName_of_person(story.getName_of_person());
	            record.setPicture(story.getPicture());
	            Story updated = repository.save(record);
	            return ResponseEntity.ok().body(updated);
	        }).orElse(ResponseEntity.notFound().build());
	  }
      
}
