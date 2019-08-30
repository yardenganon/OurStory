package com.example.ourstoryapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.domain.Memory;

@RestController
public class MemoryController {

	@Autowired
	MemoryRepository repository;

	@GetMapping("/memory/findAll")
	public Iterable<Memory> findAll() {
		return repository.findAll();
	}

	@PostMapping("/memory/create")
	public Memory create(@Valid @RequestBody Memory memory) {
		return repository.save(memory);
	}

	@GetMapping("/memory/findById/{id}")
	public ResponseEntity<Memory> findById(@PathVariable(value = "id") long memoryId) {
		return repository.findById(memoryId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/memory/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long memoryId) {
		return repository.findById(memoryId).map(record -> {
			repository.deleteById(memoryId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
