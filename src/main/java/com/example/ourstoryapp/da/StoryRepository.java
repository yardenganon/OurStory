package com.example.ourstoryapp.da;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Memory;
import com.example.ourstoryapp.domain.Story;

@RepositoryRestResource
public interface StoryRepository extends CrudRepository <Story ,Long> { 
	
	
	@Query(value = "SELECT STORY.* FROM STORY WHERE (Story.name_of_person LIKE CONCAT('%',?1,'%'))", nativeQuery = true)
	List<Story> findStoryByKeyword(String name_of_person);

}
