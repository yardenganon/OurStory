package com.example.ourstoryapp.web;

import java.net.URI;

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

import com.example.ourstoryapp.da.VideoRepository;
import com.example.ourstoryapp.domain.Video;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	VideoRepository repository;
    Logger logger = LogManager.getLogger(VideoController.class);

	@GetMapping("/findAll")
	public Iterable<Video> findAll() {
		logger.info("Find All Videos");
		return repository.findAll();
	}

	@PostMapping("/create")
	public Video create(@Valid @RequestBody Video video) {
		logger.info("Create Video");
		return repository.save(video);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Video> findById(@PathVariable(value = "id") URI videoId) {
		if(repository.findById(videoId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logger.info("Find Video By ID");
			return repository.findById(videoId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		else {
			logger.info("Video By ID Is Not Existing");
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") URI videoId) {
		if(repository.findById(videoId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logger.info("Successfully Deleted Video");
			return repository.findById(videoId).map(record -> {
				repository.deleteById(videoId);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		}
		else {
			logger.info("Video Is Not Existing");
			return ResponseEntity.notFound().build();
		}

	}

}
