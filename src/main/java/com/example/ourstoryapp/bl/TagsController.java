package com.example.ourstoryapp.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.TagRepository;
import com.example.ourstoryapp.domain.Tag;

@RestController 
@RequestMapping("/tags")
public class TagsController {

	@Autowired
    private TagRepository repository;

	//return all tags	
	public Iterable<Tag> getTags() {
		return repository.findAll();
    } 
	
	//delete tag
	public void delete(Tag tag) {
		repository.delete(tag);
	}
	
	//add tag
	@RequestMapping(value = "/tags/add", method = RequestMethod.GET)
	public void addtag(Tag tag) {
		repository.save(tag);	
	}
	
	// find tag by name
	public void findbyid(String tag_name) {
		repository.findById(tag_name);
	}


}
