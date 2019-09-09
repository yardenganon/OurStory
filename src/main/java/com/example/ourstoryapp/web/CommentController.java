package com.example.ourstoryapp.web;

import java.util.Optional;

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

import com.example.ourstoryapp.da.CommentRepository;
import com.example.ourstoryapp.domain.Comment;
import com.example.ourstoryapp.domain.Memory;
//import com.example.ourstoryapp.service.LoggingController;


@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentRepository repository;
    Logger logger = LogManager.getLogger(CommentController.class);

	@GetMapping("/findAll")
	public Iterable<Comment> findAll() {
		logger.info("Find All Comments");
		return repository.findAll();
	}

	@PostMapping("/create")
	public Comment create(@Valid @RequestBody Comment comment) {
		logger.info("Cretae Comment");
		return repository.save(comment);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Comment> findById(@PathVariable(value = "id") long commentId) {
		if((repository.findById(commentId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
			logger.info("Find Comment By ID");
			return repository.findById(commentId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		else {
			logger.info("Comment By ID Is Not Found");
			return ResponseEntity.notFound().build();
		}
	}
	
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long commentId) {
		if((repository.findById(commentId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
			logger.info("Successfully Deleted Comment");
			repository.deleteById(commentId);
			return ResponseEntity.ok().build();
			
		}
		else {
			logger.info("Comment is not existing");
			return ResponseEntity.notFound().build();
		}
		}
	
	@RequestMapping("/getMemoryComments/{id}")
	public Iterable<Comment> getMemoryComments(@PathVariable("id") long memoryId) {
		if((repository.getMemoryComments(memoryId))!=null) {
			logger.info("Find Memory Comments By ID");
			return repository.getMemoryComments(memoryId);
		}
		else {
			logger.info("Memory Comments By ID is Not Existing");
			return repository.getMemoryComments(memoryId);
		}
	}
}
