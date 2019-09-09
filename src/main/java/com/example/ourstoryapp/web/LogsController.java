package com.example.ourstoryapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.Memory;

@RestController
@RequestMapping("/logs")
public class LogsController {

	@Autowired
	LogRepository repository;

	@GetMapping("/findAll")
	public Iterable<AppLogs> findAll() {
		// logger.info("Find All Memories");
		return repository.findAll();
	}
}
