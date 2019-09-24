package com.example.ourstoryapp.web;

import java.util.Date;

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

import com.example.ourstoryapp.da.LikesRepository;
import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.da.StoryRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.Likes;
import com.example.ourstoryapp.domain.LogStatus;
import com.example.ourstoryapp.domain.Story;

@RestController
@RequestMapping("/likes")
public class LikesController {
	@Autowired
	LikesRepository repository;
	@Autowired
	private LogRepository logRepository;
	final String name = LikesController.class.getName();

	@GetMapping("/findAll")
	public Iterable<Likes> getStories() {
		logRepository.save(new AppLogs(new Date(), name, "Find All Stories", LogStatus.SUCCESS.name(), null));
		return repository.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Likes> create(@Valid @RequestBody Likes like) {
		Likes s = repository.save(like);
		if ((repository.findById(s.getLike_id()).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository.save(new AppLogs(new Date(), name, "CreateStory", LogStatus.SUCCESS.name(), s.toString()));
			return repository.findById(s.getLike_id()).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name, "CreateStory", LogStatus.FAILURE.name(), s.toString()));
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Likes> findById(@PathVariable(value = "id") Integer likeId) {
		if ((repository.findById(likeId).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository
					.save(new AppLogs(new Date(), name, "findById", LogStatus.SUCCESS.name(), Long.toString(likeId)));
			return repository.findById(likeId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository
					.save(new AppLogs(new Date(), name, "findById", LogStatus.FAILURE.name(), Long.toString(likeId)));
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer likeId) {
		if ((repository.findById(likeId).map(record -> ResponseEntity.ok().body(record)).isPresent())) {
			logRepository
					.save(new AppLogs(new Date(), name, "delete", LogStatus.SUCCESS.name(), Long.toString(likeId)));
			return repository.findById(likeId).map(record -> {
				repository.deleteById(likeId);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		} else {
			logRepository
					.save(new AppLogs(new Date(), name, "delete", LogStatus.FAILURE.name(), Long.toString(likeId)));
			return ResponseEntity.notFound().build();
		}

	}

}
