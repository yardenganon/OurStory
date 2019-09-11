package com.example.ourstoryapp.web;

import java.net.URI;
import java.util.Date;

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

import com.example.ourstoryapp.da.LogRepository;
import com.example.ourstoryapp.da.VideoRepository;
import com.example.ourstoryapp.domain.AppLogs;
import com.example.ourstoryapp.domain.LogStatus;
import com.example.ourstoryapp.domain.Video;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	VideoRepository repository;
	@Autowired
	private LogRepository logRepository;
	 final String name = VideoController.class.getName();
	 
	@GetMapping("/findAll")
	public Iterable<Video> findAll() {
		logRepository.save(new AppLogs(new Date(), name, "findAll", LogStatus.SUCCESS.name(), null));
		return repository.findAll();
	}

	@PostMapping("/create")
	public Video create(@Valid @RequestBody Video video) {
		logRepository.save(new AppLogs(new Date(), name, "create", LogStatus.SUCCESS.name(), video.toString()));
		return repository.save(video);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Video> findById(@PathVariable(value = "id") URI videoId) {
		if(repository.findById(videoId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logRepository.save(new AppLogs(new Date(), name, "findById", LogStatus.SUCCESS.name(), videoId.toString()));
			return repository.findById(videoId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		else {
			logRepository.save(new AppLogs(new Date(), name, "findById", LogStatus.FAILURE.name(), videoId.toString()));
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") URI videoId) {
		if(repository.findById(videoId).map(record -> ResponseEntity.ok().body(record)).isPresent()) {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.SUCCESS.name(), videoId.toString()));
			return repository.findById(videoId).map(record -> {
				repository.deleteById(videoId);
				return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
		}
		else {
			logRepository.save(new AppLogs(new Date(), name, "delete", LogStatus.FAILURE.name(), videoId.toString()));
			return ResponseEntity.notFound().build();
		}

	}

}
