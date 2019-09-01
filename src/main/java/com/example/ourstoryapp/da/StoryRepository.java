package com.example.ourstoryapp.da;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Story;

@RepositoryRestResource
public interface StoryRepository extends CrudRepository <Story ,Long> { 

}
