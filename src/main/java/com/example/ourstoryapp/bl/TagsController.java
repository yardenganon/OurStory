package com.example.ourstoryapp.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.TagRepository;
import com.example.ourstoryapp.domain.Tag;

@RestController 
public class TagsController {

	@Autowired
    private TagRepository repository;

	
	@RequestMapping("/tags")
	public Iterable<Tag> getTags() {
		return repository.findAll();
    } 

}
