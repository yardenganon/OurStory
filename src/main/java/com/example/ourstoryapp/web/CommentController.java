package com.example.ourstoryapp.web;

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

import com.example.ourstoryapp.da.CommentRepository;
import com.example.ourstoryapp.domain.Comment;
import com.example.ourstoryapp.domain.Memory;


@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentRepository repository;

	@GetMapping("/findAll")
	public Iterable<Comment> findAll() {
		return repository.findAll();
	}

	@PostMapping("/create")
	public Comment create(@Valid @RequestBody Comment comment) {
		return repository.save(comment);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Comment> findById(@PathVariable(value = "id") long commentId) {
		return repository.findById(commentId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long commentId) {
		return repository.findById(commentId).map(record -> {
			repository.deleteById(commentId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	
	@RequestMapping("/getMemoryComments/{id}")
	public Iterable<Comment> getMemoryComments(@PathVariable("id") long memoryId) {
		return repository.getMemoryComments(memoryId);
	}

}
