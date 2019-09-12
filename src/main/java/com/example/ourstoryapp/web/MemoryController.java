package com.example.ourstoryapp.web;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.example.ourstoryapp.da.PictureRepository;
import com.example.ourstoryapp.da.TagRepository;
import com.example.ourstoryapp.da.VideoRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.LogStatus;
import com.example.ourstoryapp.domain.Memory;
import com.example.ourstoryapp.domain.Picture;
import com.example.ourstoryapp.domain.Tag;
import com.example.ourstoryapp.domain.Video;

@RestController
@RequestMapping("/memories")
public class MemoryController {

	@Autowired
	MemoryRepository repository;
	@Autowired
	PictureRepository picture_repository;
	@Autowired
	VideoRepository video_repository;
	@Autowired
	TagRepository tag_repository;
	@Autowired
	private LogRepository logRepository;

	final String name = MemoryController.class.getName();

	@GetMapping("/findAll")
	public Iterable<Memory> findAll() {
		logRepository.save(new AppLogs(new Date(), name, "findAll", LogStatus.SUCCESS.name(), null));
		return repository.findAll();
	}

	@PostMapping("/create")
	public Memory create(@Valid @RequestBody Memory memory) {
		logRepository.save(new AppLogs(new Date(), name, "create", LogStatus.SUCCESS.name(), memory.toString()));
		return repository.save(memory);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Memory> findById(@PathVariable(value = "id") long memoryId) {
		if (repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logRepository
					.save(new AppLogs(new Date(), name, "findById", LogStatus.SUCCESS.name(), Long.toString(memoryId)));
			return repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository
					.save(new AppLogs(new Date(), name, "findById", LogStatus.FAILURE.name(), Long.toString(memoryId)));
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long memoryId) {
		if (repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logRepository
					.save(new AppLogs(new Date(), name, "delete", LogStatus.SUCCESS.name(), Long.toString(memoryId)));
			return repository.findById(memoryId).map(record -> {
				repository.deleteById(memoryId);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		} else {
			logRepository
					.save(new AppLogs(new Date(), name, "delete", LogStatus.FAILURE.name(), Long.toString(memoryId)));
			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping("/getUserMemories/{id}")
	public Iterable<Memory> getUserMemories(@PathVariable long id) {
		if (repository.getUserMemories(id) != null) {
			logRepository.save(
					new AppLogs(new Date(), name, "getUserMemories", LogStatus.SUCCESS.name(), Long.toString(id)));
			return repository.getUserMemories(id);
		} else {
			logRepository.save(
					new AppLogs(new Date(), name, "getUserMemories", LogStatus.FAILURE.name(), Long.toString(id)));
			return repository.getUserMemories(id);
		}
	}

	@RequestMapping("/getStoryMemoriesSortedByYear/{id}")
	public Iterable<Memory> getStoryMemoriesSortedByYear(@PathVariable long id) {
		if (repository.getStoryMemoriesSortedByYear(id) != null) {
			logRepository.save(new AppLogs(new Date(), name, "getStoryMemoriesSortedByYear", LogStatus.SUCCESS.name(),
					Long.toString(id)));
			return repository.getStoryMemoriesSortedByYear(id);
		} else {
			logRepository.save(new AppLogs(new Date(), name, "getStoryMemoriesSortedByYear", LogStatus.FAILURE.name(),
					Long.toString(id)));
			return repository.getStoryMemoriesSortedByYear(id);
		}
	}

	@RequestMapping("story/{story}/findMemoriesByYear/{year}")
	public Iterable<Memory> findMemoriesByYear(@PathVariable long story, @PathVariable int year) {
		logRepository.save(new AppLogs(new Date(), name, "findMemoriesByYear", LogStatus.SUCCESS.name(),
				"story id : " + Long.toString(story) + " year : " + Integer.toString(year)));

		return repository.findMemoriesByYear(story, year);

	}

	@RequestMapping("story/{story}/findMemoriesByTag/{tag}")
	public Iterable<Memory> findMemoriesByTag(@PathVariable long story, @PathVariable String tag) {
		// logRepository.save(new AppLogs(new Date(), name, "findMemoriesByTag",
		// LogStatus.FAILURE.name(),"story id : " + Long.toString(story) + " tag : " +
		// tag));
		logRepository.save(new AppLogs(new Date(), name, "findMemoriesByTag", LogStatus.SUCCESS.name(),
				"story id : " + Long.toString(story) + " tag : " + tag));
		return repository.findMemoriesByTag(story, tag);
	}

	@RequestMapping("/findMemoriesByKeyword/{description}")
	public Iterable<Memory> findMemoriesByKeyword(String description) {
		// logRepository.save(new AppLogs(new Date(), name, "findMemoriesByKeyword",
		// LogStatus.FAILURE.name(), description));
		logRepository
				.save(new AppLogs(new Date(), name, "findMemoriesByKeyword", LogStatus.SUCCESS.name(), description));

		return repository.findMemoriesByKeyword(description);

	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Memory> update(@PathVariable("id") long memory_id, @RequestBody Memory memory) {
		// logRepository.save(new AppLogs(new Date(), name, "update",
		// LogStatus.FAILURE.name(), memory.toString()));
		logRepository.save(new AppLogs(new Date(), name, "update", LogStatus.SUCCESS.name(), memory.toString()));

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

	@PutMapping(value = "/createMemory")
	public ResponseEntity<Memory> createMemory(@RequestBody Memory memory, @RequestBody List<String> pictures,
			@RequestBody List<String> videos, @RequestBody List<String> tags) {
		logRepository.save(new AppLogs(new Date(), name, "createMemory", LogStatus.SUCCESS.name(), memory.toString()));
		Memory m = repository.save(memory);
		List<Picture> p = new ArrayList<Picture>();
		List<Video> v = new ArrayList<Video>();
		Set<Tag> t = new HashSet<Tag>();
		for (String s : pictures) {
			Picture picture = new Picture(s, m);
			picture_repository.save(picture);
			p.add(picture);
		}
		for (String s : videos) {
			Video video = new Video(s, m);
			video_repository.save(video);
			v.add(video);
		}
		for (String s : tags) {
			Tag tag = new Tag(s);
			if (!tag_repository.findById(s).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
				tag_repository.save(tag);
			}
			t.add(tag);
		}
		return repository.findById(m.getMemory_id()).map(record -> {
			record.setPictures(p);
			record.setVideos(v);
			record.setTags(t);
			Memory updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/addMediaToMemory")
	public ResponseEntity<Memory> addMediaToMemory(@PathVariable long memory, @RequestBody List<String> pictures,
			@RequestBody List<String> videos, @RequestBody List<String> tags) {
		logRepository.save(
				new AppLogs(new Date(), name, "addMediaToMemory", LogStatus.SUCCESS.name(), Long.toString(memory)));
		Memory m = repository.findById(memory).get();
		List<Picture> p = new ArrayList<Picture>();
		List<Video> v = new ArrayList<Video>();
		Set<Tag> t = new HashSet<Tag>();
		for (String s : pictures) {
			Picture picture = new Picture(s, m);
			picture_repository.save(picture);
			p.add(picture);
		}
		for (String s : videos) {
			Video video = new Video(s, m);
			video_repository.save(video);
			v.add(video);
		}
		for (String s : tags) {
			Tag tag = new Tag(s);
			if (!tag_repository.findById(s).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
				tag_repository.save(tag);
			}
			t.add(tag);
		}
		return repository.findById(m.getMemory_id()).map(record -> {
			record.setPictures(p);
			record.setVideos(v);
			record.setTags(t);
			Memory updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@RequestMapping("ViewStory/{story}")
	public HashMap<Integer, List<String[]>> ViewStory2(@PathVariable("story") long story) {
		logRepository.save(new AppLogs(new Date(), name, "ViewStory", LogStatus.SUCCESS.name(),
				"story id : " + Long.toString(story)));
		List<Integer> relevantYears = ViewStoryHelperMethod(story);
		HashMap<Integer, List<String[]>> hm = new HashMap<Integer, List<String[]>>();
		for (Integer year : relevantYears) {
			hm.put(year, repository.ViewStory(story, year));
		}
		return hm;
	}

	@RequestMapping("ViewStoryHelperMethod/{story}")
	public List<Integer> ViewStoryHelperMethod(@PathVariable long story) {
		logRepository.save(new AppLogs(new Date(), name, "ViewStoryHelperMethod", LogStatus.SUCCESS.name(),
				"story id : " + Long.toString(story)));
		return repository.ViewStoryHelperMethod(story);
	}

}
