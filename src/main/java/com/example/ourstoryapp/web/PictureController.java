package com.example.ourstoryapp.web;

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

import com.example.ourstoryapp.da.PictureRepository;
import com.example.ourstoryapp.domain.Picture;

@RestController
public class PictureController {

	@Autowired
	PictureRepository repository;

	@GetMapping("/picture/findAll")
	public Iterable<Picture> findAll() {
		return repository.findAll();
	}

	@PostMapping("/picture/create")
	public Picture create(@Valid @RequestBody Picture picture) {
		return repository.save(picture);
	}

	@GetMapping("/picture/findById/{id}")
	public ResponseEntity<Picture> findById(@PathVariable(value = "id") URI pictureId) {
		return repository.findById(pictureId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/picture/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") URI pictureId) {
		return repository.findById(pictureId).map(record -> {
			repository.deleteById(pictureId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
