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

import com.example.ourstoryapp.da.MemTagRepository;
import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.da.TagRepository;

import com.example.ourstoryapp.domain.Tag;

@RestController 
@RequestMapping("/tags_in_memory")
public class TagsInMemoryController {
	@Autowired
    private MemTagRepository mem_tags_repository;
	
	//return all tags	
	@RequestMapping("/tags_in_memory/findAll")
	public Iterable<Tag> getmemTags() {
		return mem_tags_repository.findAll();
    }

	//delete tag
	@DeleteMapping("/tags_in_memory/delete/{id}")
	public ResponseEntity<?> deletememtag(@PathVariable("id") String tag_name) {
		return mem_tags_repository.findById(tag_name).map(record -> {
			mem_tags_repository.deleteById(tag_name);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	//add tag
	@PostMapping("/tags_in_memory/create")
	public Tag Create(@Valid @RequestBody Tag tag) {
		return mem_tags_repository.save(tag);	
	}
	
	// find tag by name
	@GetMapping("/tags_in_memory/findById/{id}")
	public ResponseEntity<Tag> findbyid(@PathVariable(value = "id") String tag_name) {
		return mem_tags_repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	
}
