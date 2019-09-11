package com.example.ourstoryapp.web;

import java.util.Date;
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
import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.Comment;
import com.example.ourstoryapp.domain.LogStatus;
import com.example.ourstoryapp.domain.Memory;
//import com.example.ourstoryapp.service.LoggingController;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentRepository repository;
	@Autowired
	private LogRepository logRepository;

	final String name = CommentController.class.getName();

	@GetMapping("/findAll")
	public Iterable<Comment> findAll() {
		logRepository.save(new AppLogs(new Date(), name, "findAll", LogStatus.SUCCESS, null));
		return repository.findAll();
	}

	@PostMapping("/create")
	public Comment create(@Valid @RequestBody Comment comment) {
		logRepository.save(new AppLogs(new Date(), name, "create", LogStatus.SUCCESS, comment.toString()));
		return repository.save(comment);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Comment> findById(@PathVariable(value = "id") long commentId) {
		if ((repository.findById(commentId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
			logRepository.save(new AppLogs(new Date(), name, "findById", LogStatus.SUCCESS, Long.toString(commentId)));
			return repository.findById(commentId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logRepository.save(new AppLogs(new Date(), name, "findById", LogStatus.FAILURE, Long.toString(commentId)));
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long commentId) {
		if ((repository.findById(commentId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.SUCCESS, Long.toString(commentId)));
			repository.deleteById(commentId);
			return ResponseEntity.ok().build();

		} else {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.FAILURE, Long.toString(commentId)));
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping("/getMemoryComments/{id}")
	public Iterable<Comment> getMemoryComments(@PathVariable("id") long memoryId) {
		if ((repository.getMemoryComments(memoryId)) != null) {
			logRepository.save(
					new AppLogs(new Date(), name, "getMemoryComments", LogStatus.SUCCESS, Long.toString(memoryId)));
			return repository.getMemoryComments(memoryId);
		} else {
			logRepository.save(
					new AppLogs(new Date(), name, "getMemoryComments", LogStatus.FAILURE, Long.toString(memoryId)));
			return repository.getMemoryComments(memoryId);
		}
	}
}
