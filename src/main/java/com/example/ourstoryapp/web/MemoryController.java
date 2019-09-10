package com.example.ourstoryapp.web;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.LogStatus;
import com.example.ourstoryapp.domain.Memory;

@RestController
@RequestMapping("/memories")
public class MemoryController {

	@Autowired
	MemoryRepository repository;
	@Autowired
	private LogRepository logRepository;

	final String name = MemoryController.class.getName();

	@GetMapping("/findAll")
	public Iterable<Memory> findAll() {
		logRepository.save(new AppLogs(new Date(), name, "findAll", LogStatus.SUCCESS, null));
		return repository.findAll();
	}

	@PostMapping("/create")
	public Memory create(@Valid @RequestBody Memory memory) {
		logRepository.save(new AppLogs(new Date(), name, "create", LogStatus.SUCCESS, memory.toString()));
		return repository.save(memory);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Memory> findById(@PathVariable(value = "id") long memoryId) {
		if (repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logRepository.save(new AppLogs(new Date(), name, "findById", LogStatus.SUCCESS, Long.toString(memoryId)));
			return repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name, "findById", LogStatus.FAILURE, Long.toString(memoryId)));
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long memoryId) {
		if (repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.SUCCESS, Long.toString(memoryId)));
			return repository.findById(memoryId).map(record -> {
				repository.deleteById(memoryId);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.FAILURE, Long.toString(memoryId)));
			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping("/getUserMemories/{id}")
	public Iterable<Memory> getUserMemories(@PathVariable long id) {
		if (repository.getUserMemories(id) != null) {
			logRepository.save(new AppLogs(new Date(), name, "getUserMemories", LogStatus.SUCCESS, Long.toString(id)));
			return repository.getUserMemories(id);
		} else {
			logRepository.save(new AppLogs(new Date(), name, "getUserMemories", LogStatus.FAILURE, Long.toString(id)));
			return repository.getUserMemories(id);
		}
	}

	@RequestMapping("story/{story}/findMemoriesByYear/{year}")
	public Iterable<Memory> findMemoriesByYear(@PathVariable long story, @PathVariable int year) {
		logRepository.save(new AppLogs(new Date(), name, "findMemoriesByYear", LogStatus.SUCCESS,
				"story id : " + Long.toString(story) + " year : " + Integer.toString(year)));

		return repository.findMemoriesByYear(story, year);

	}

	@RequestMapping("story/{story}/findMemoriesByTag/{tag}")
	public Iterable<Memory> findMemoriesByTag(@PathVariable long story, @PathVariable String tag) {
		logRepository.save(new AppLogs(new Date(), name, "findMemoriesByTag", LogStatus.FAILURE,
				"story id : " + Long.toString(story) + " tag : " + tag));

		return repository.findMemoriesByTag(story, tag);
	}

	@RequestMapping("/findMemoriesByKeyword/{description}")
	public Iterable<Memory> findMemoriesByKeyword(String description) {
		logRepository.save(new AppLogs(new Date(), name, "findMemoriesByKeyword", LogStatus.FAILURE, description));

		return repository.findMemoriesByKeyword(description);

	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Memory> update(@PathVariable("id") long memory_id, @RequestBody Memory memory) {
		logRepository.save(new AppLogs(new Date(), name, "update", LogStatus.FAILURE, memory.toString()));

		return repository.findById(memory_id).map(record -> {
			record.setDescription(memory.getDescription());
			record.setMemory_date(memory.getMemory_date());
			record.setFeeling(memory.getFeeling());
			record.setLocation(memory.getLocation());
			record.setTags(memory.getTags());
			record.setPictures(memory.getPictures());
			record.setVideos(memory.getVideos());
			Memory updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
}
