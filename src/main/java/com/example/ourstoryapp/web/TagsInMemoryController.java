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
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.da.MemTagRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.Tag;

@RestController
@RequestMapping("/tags_in_memory")
public class TagsInMemoryController {
	@Autowired
	private MemTagRepository mem_tags_repository;
	@Autowired
	private LogRepository logRepository;
	Logger logger = LogManager.getLogger(TagsInMemoryController.class);
	final String name = TagsInMemoryController.class.getName();

	// return all tags
	@RequestMapping("/findAll")
	public Iterable<Tag> getmemTags() {
		logRepository.save(new AppLogs(new Date(), name, "Find All Tags in Memory"));
		return mem_tags_repository.findAll();
	}

	// delete tag
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletememtag(@PathVariable("id") String tag_name) {
		if ((mem_tags_repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository.save(new AppLogs(new Date(), name,"Successfully Deleted Tag in Memory"));
			return mem_tags_repository.findById(tag_name).map(record -> {
				mem_tags_repository.deleteById(tag_name);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name,"Tag in memory is not existing"));
			return ResponseEntity.notFound().build();
		}
	}

	// add tag
	@PostMapping("/create")
	public Tag Create(@Valid @RequestBody Tag tag) {
		logRepository.save(new AppLogs(new Date(), name,"Create New Tag in Memory"));
		return mem_tags_repository.save(tag);
	}

	// find tag by name
	@GetMapping("/findById/{id}")
	public ResponseEntity<Tag> findbyid(@PathVariable(value = "id") String tag_name) {
		if ((mem_tags_repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository.save(new AppLogs(new Date(), name,"Find Tag in Memory by ID"));
			return mem_tags_repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name,"Tag in Memrory is not existing"));
			return ResponseEntity.notFound().build();
		}
	}

}
