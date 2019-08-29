package com.example.ourstoryapp;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.VideoRepository;
import com.example.ourstoryapp.domain.Video;

@RestController
public class VideoController {

	@Autowired
	VideoRepository repository;

	@GetMapping("/video/findAll")
	public Iterable<Video> findAll() {
		return repository.findAll();
	}

	@PostMapping("/video/create")
	public Video create(@Valid @RequestBody Video video) {
		return repository.save(video);
	}

	@GetMapping("/video/findById/{id}")
	public ResponseEntity<Video> findById(@PathVariable(value = "id") URI videoId) {
		return repository.findById(videoId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/video/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") URI videoId) {
		return repository.findById(videoId).map(record -> {
			repository.deleteById(videoId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
