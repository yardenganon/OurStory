package com.example.ourstoryapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.ourstoryapp.da.StoryRepository;
import com.example.ourstoryapp.domain.Memory;
import com.example.ourstoryapp.domain.Story;


@RestController
@RequestMapping("/stories")
public class StoryController {
	
	@Autowired
	StoryRepository repository; 
	
	
	// Basic CRUD
	
	// get all stories - sorted by ID (Read)
    @GetMapping("/findAll")
    public Iterable<Story> getStories() {
      return repository.findAll();
    }
    
    // create new instance of Story (Create)
	@PostMapping("/create")
	public Story create(@Valid @RequestBody Story story) {
		return repository.save(story);
	}

	// get story by ID (Read)
	@GetMapping("/findById/{id}")
	public ResponseEntity<Story> findById(@PathVariable(value = "id") long storyId) {
		return repository.findById(storyId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	// delete story by ID (Delete)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long storyId) {
		return repository.findById(storyId).map(record -> {
			repository.deleteById(storyId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	// update story by id using new values (Update)
	@PutMapping(value="/{id}")
	  public ResponseEntity<Story> update(@PathVariable("id") long storyId,
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
	
	//TODO findByKeyword find story by name
	
	@RequestMapping("/findStoriesByKeyword")
	public Iterable<Story> findStoriesByKeyword(@RequestParam("name") String name_of_person) {
		return repository.findStoriesByKeyword(name_of_person);
	}
	
	//TODO findMemoryTagsByStoryId
	@RequestMapping("/findStoriesByDobFull")
	public Iterable<Story> findStoriesByDobFull(@RequestParam("d") int d,@RequestParam("m") int m,@RequestParam("y") int y) {
		return repository.findStoriesByDobFull(d,m,y);
	}
	
	
	@RequestMapping("/findStoriesByDobYearMonth")
	public Iterable<Story> findStoriesByDobYearMonth(@RequestParam("m") int m,@RequestParam("y") int y) {
		return repository.findStoriesByDobYearMonth(m,y);
	}
	
	@RequestMapping("/findStoriesByDobYear")
	public Iterable<Story> findStoriesByDobYear(@RequestParam("y") int y) {
		return repository.findStoriesByDobYear(y);
	}
	
	
	
	@RequestMapping("/findStoriesByDodFull")
	public Iterable<Story> findStoriesByDodFull(@RequestParam("d") int d,@RequestParam("m") int m,@RequestParam("y") int y) {
		return repository.findStoriesByDodFull(d,m,y);
	}
	
	
	@RequestMapping("/findStoriesByDodYearMonth")
	public Iterable<Story> findStoriesByDodYearMonth(@RequestParam("m") int m,@RequestParam("y") int y) {
		return repository.findStoriesByDodYearMonth(m,y);
	}
	
	@RequestMapping("/findStoriesByDodYear")
	public Iterable<Story> findStoriesByDodYear(@RequestParam("y") int y) {
		return repository.findStoriesByDodYear(y);
	}
	
	
	
	
	
	
}
