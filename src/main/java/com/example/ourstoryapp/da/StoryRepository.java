package com.example.ourstoryapp.da;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Story;

@RepositoryRestResource
public interface StoryRepository extends JpaRepository <Story ,Long> { 

	@Query(value = "SELECT STORY.* FROM STORY WHERE Story.name_of_person LIKE concat('%',?1,'%')", nativeQuery = true)
	List<Story> findStoriesByKeyword(String name_of_person);



}
