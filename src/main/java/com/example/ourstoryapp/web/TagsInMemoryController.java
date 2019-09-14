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
import com.example.ourstoryapp.domain.LogStatus;
import com.example.ourstoryapp.domain.Tag;

@RestController
@RequestMapping("/tags_in_memory")
public class TagsInMemoryController {
	@Autowired
	private MemTagRepository mem_tags_repository;
	@Autowired
	private LogRepository logRepository;

	final String name = TagsInMemoryController.class.getName();

	// return all tags
	@RequestMapping("/findAll")
	public Iterable<Tag> getmemTags() {
		logRepository.save(new AppLogs(new Date(), name, "findAll", LogStatus.SUCCESS.name(), null));
		return mem_tags_repository.findAll();
	}

	// delete tag
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletememtag(@PathVariable("id") String tag_name) {
		if ((mem_tags_repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.SUCCESS.name(), tag_name));
			return mem_tags_repository.findById(tag_name).map(record -> {
				mem_tags_repository.deleteById(tag_name);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.FAILURE.name(), tag_name));
			return ResponseEntity.notFound().build();
		}
	}

	// add tag
	@PostMapping("/create")
	public Tag Create(@Valid @RequestBody Tag tag) {
		logRepository.save(new AppLogs(new Date(), name, "create", LogStatus.SUCCESS.name(), tag.toString()));
		return mem_tags_repository.save(tag);
	}

	// find tag by name
	@GetMapping("/findById/{id}")
	public ResponseEntity<Tag> findbyid(@PathVariable(value = "id") String tag_name) {
		if ((mem_tags_repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository.save(new AppLogs(new Date(), name, "findbyid", LogStatus.SUCCESS.name(), tag_name));
			return mem_tags_repository.findById(tag_name).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name, "findbyid", LogStatus.FAILURE.name(), tag_name));
			return ResponseEntity.notFound().build();
		}
	}
	
	
	

}
