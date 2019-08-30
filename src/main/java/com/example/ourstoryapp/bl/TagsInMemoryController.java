package com.example.ourstoryapp.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.MemTagRepository;
import com.example.ourstoryapp.da.MemoryRepository;
import com.example.ourstoryapp.da.TagRepository;

import com.example.ourstoryapp.domain.Tag;

@RestController 
@RequestMapping("/tags")
public class TagsInMemoryController {
	@Autowired
    private MemTagRepository mem_tag_repository;
	
	//return all tags	
	public Iterable<Tag> getmemTags() {
		return mem_tag_repository.findAll();
    }

	
	
	//delete tag
	public void deletememtag(Tag tag) {
		mem_tag_repository.delete(tag);
	}
	
	//add tag
	public void addmemtag(Tag tag) {
		mem_tag_repository.save(tag);	
	}
	
	// find tag by name
	public void findbyid(String tag_name) {
		mem_tag_repository.findById(tag_name);
	}

	
}
