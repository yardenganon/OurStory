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

import com.example.ourstoryapp.da.TagRepository;
import com.example.ourstoryapp.domain.Tag;

@RestController 
@RequestMapping("/tags")
public class TagsController {

	@Autowired
    private TagRepository repository;

	//return all tags	
	@RequestMapping("/tags/findAll")
	public Iterable<Tag> getTags() {
		return repository.findAll();
    } 
	
	//delete tag
	@DeleteMapping("/tags/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String tag_name) {
		return repository.findById(tag_name).map(record -> {
			repository.deleteById(tag_name);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	//add tag
	@PostMapping("/tags/create")
	public Tag create(@Valid @RequestBody Tag tag) {
		return repository.save(tag);	
	}
	
	// find tag by name
	@GetMapping("/tags/findById/{id}")
	public ResponseEntity<Tag> findByid( @PathVariable(value = "id") String tag_name) {
		return repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}


}
