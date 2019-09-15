package com.example.ourstoryapp.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.da.StoryRepository;
import com.example.ourstoryapp.da.TagRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.LogStatus;
import com.example.ourstoryapp.domain.Story;
import com.example.ourstoryapp.domain.ViewStory;
import com.example.ourstoryapp.domain.ViewStoryObject;

@RestController
@RequestMapping("/stories")
public class StoryController {

	@Autowired
	StoryRepository repository;
	@Autowired
	private LogRepository logRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	private MemoryController memoryController;

	final String name = StoryController.class.getName();

	// Basic CRUD

	// get all stories - sorted by ID (Read)
	@GetMapping("/findAll")
	public Iterable<Story> getStories() {
		logRepository.save(new AppLogs(new Date(), name, "Find All Stories", LogStatus.SUCCESS.name(), null));
		return repository.findAll();
	}

	// create new instance of Story (Create)
	@PostMapping("/create")
	public Story create(@Valid @RequestBody Story story) {
		logRepository.save(new AppLogs(new Date(), name, "create", LogStatus.SUCCESS.name(), story.toString()));
		return repository.save(story);
	}

	// get story by ID (Read)
	@GetMapping("/findById/{id}")
	public ResponseEntity<Story> findById(@PathVariable(value = "id") long storyId) {
		if ((repository.findById(storyId).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository
					.save(new AppLogs(new Date(), name, "findById", LogStatus.SUCCESS.name(), Long.toString(storyId)));
			return repository.findById(storyId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository
					.save(new AppLogs(new Date(), name, "findById", LogStatus.FAILURE.name(), Long.toString(storyId)));
			return ResponseEntity.notFound().build();
		}
	}

	// delete story by ID (Delete)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long storyId) {
		if ((repository.findById(storyId).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository
					.save(new AppLogs(new Date(), name, "delete", LogStatus.SUCCESS.name(), Long.toString(storyId)));
			return repository.findById(storyId).map(record -> {
				repository.deleteById(storyId);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		} else {
			logRepository
					.save(new AppLogs(new Date(), name, "delete", LogStatus.FAILURE.name(), Long.toString(storyId)));
			return ResponseEntity.notFound().build();
		}

	}

	// update story by id using new values (Update)
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Story> update(@PathVariable("id") long storyId, @RequestBody Story story) {
		logRepository.save(new AppLogs(new Date(), name, "update", LogStatus.SUCCESS.name(),
				"storyId : " + Long.toString(storyId) + "story : " + storyId));
		return repository.findById(storyId).map(record -> {
			record.setDate_of_birth(story.getDate_of_birth());
			record.setDate_of_death(story.getDate_of_death());
			record.setName_of_person(story.getName_of_person());
			record.setPicture(story.getPicture());
			Story updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	// TODO findByKeyword find story by name

	@RequestMapping("/findStoriesByKeyword")
	public Iterable<Story> findStoriesByKeyword(@RequestParam("name") String name_of_person) {
		logRepository
				.save(new AppLogs(new Date(), name, "findStoriesByKeyword", LogStatus.SUCCESS.name(), name_of_person));

		return repository.findStoriesByKeyword(name_of_person);
	}

	// TODO findMemoryTagsByStoryId
	@RequestMapping("/findStoriesByDobFull")
	public Iterable<Story> findStoriesByDobFull(@RequestParam("d") int d, @RequestParam("m") int m,
			@RequestParam("y") int y) {
		logRepository.save(new AppLogs(new Date(), name, "findStoriesByDobFull", LogStatus.SUCCESS.name(),
				"day : " + d + " month : " + m + " year : " + y));

		return repository.findStoriesByDobFull(d, m, y);
	}

	@RequestMapping("/findStoriesByDobYearMonth")
	public Iterable<Story> findStoriesByDobYearMonth(@RequestParam("m") int m, @RequestParam("y") int y) {
		logRepository.save(new AppLogs(new Date(), name, "findStoriesByDobYearMonth", LogStatus.SUCCESS.name(),
				" month : " + m + " year : " + y));

		return repository.findStoriesByDobYearMonth(m, y);
	}

	@RequestMapping("/findStoriesByDobYear")
	public Iterable<Story> findStoriesByDobYear(@RequestParam("y") int y) {
		logRepository
				.save(new AppLogs(new Date(), name, "findStoriesByDobYear", LogStatus.SUCCESS.name(), " year : " + y));

		return repository.findStoriesByDobYear(y);
	}

	@RequestMapping("/findStoriesByDodFull")
	public Iterable<Story> findStoriesByDodFull(@RequestParam("d") int d, @RequestParam("m") int m,
			@RequestParam("y") int y) {
		logRepository.save(new AppLogs(new Date(), name, "findStoriesByDodFull", LogStatus.SUCCESS.name(),
				"day : " + d + " month : " + m + " year : " + y));

		return repository.findStoriesByDodFull(d, m, y);
	}

	@RequestMapping("/findStoriesByDodYearMonth")
	public Iterable<Story> findStoriesByDodYearMonth(@RequestParam("m") int m, @RequestParam("y") int y) {
		logRepository.save(new AppLogs(new Date(), name, "findStoriesByDodYearMonth", LogStatus.SUCCESS.name(),
				" month : " + m + " year : " + y));

		return repository.findStoriesByDodYearMonth(m, y);
	}

	@RequestMapping("/findStoriesByDodYear")
	public Iterable<Story> findStoriesByDodYear(@RequestParam("y") int y) {
		logRepository
				.save(new AppLogs(new Date(), name, "findStoriesByDodYear", LogStatus.SUCCESS.name(), " year : " + y));

		return repository.findStoriesByDodYear(y);
	}

	@RequestMapping("/findStoriesByDateOfBirth")
	public Iterable<Story> findStoriesByDateOfBirth(@RequestParam("d") int d, @RequestParam("m") int m,
			@RequestParam("y") int y, @RequestParam("name") String name_of_person) {
		logRepository.save(new AppLogs(new Date(), name, "findStoriesByDateOfBirth", LogStatus.SUCCESS.name(),
				" day : " + d + " month : " + m + " year : " + y + " name : " + name_of_person));
		return repository.findStoriesByDateOfBirth(d, m, y, name_of_person);
	}

	@RequestMapping("/findStoriesByDateOfDeath")
	public Iterable<Story> findStoriesByDateOfDeath(@RequestParam("d") int d, @RequestParam("m") int m,
			@RequestParam("y") int y, @RequestParam("name") String name_of_person) {
		logRepository.save(new AppLogs(new Date(), name, "findStoriesByDateOfDeath", LogStatus.SUCCESS.name(),
				" day : " + d + " month : " + m + " year : " + y + " name : " + name_of_person));
		return repository.findStoriesByDateOfDeath(d, m, y, name_of_person);
	}

	@RequestMapping("ViewStoryFull/{story}")
	public ViewStory ViewStoryFull(@PathVariable("story") long story) {
		logRepository.save(new AppLogs(new Date(), name, "ViewStoryFull", LogStatus.SUCCESS.name(),
				"story id : " + Long.toString(story)));
		ViewStory v = new com.example.ourstoryapp.domain.ViewStory(repository.findById(story).get(),
				tagRepository.findTop3TagsByStoryId(story), memoryController.ViewStory(story));
		return v;
	}
}
