package com.example.ourstoryapp.da;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Story;

@RepositoryRestResource
public interface StoryRepository extends JpaRepository <Story ,Long> { 

	@Query(value = "SELECT STORY.* FROM STORY WHERE Story.name_of_person LIKE concat('%',?1,'%')", nativeQuery = true)
	List<Story> findStoriesByKeyword(String name_of_person);
	// find story by full date date of birth
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT (day FROM Story.date_of_birth) = ?1  AND EXTRACT (month FROM Story.date_of_birth) = ?2 AND EXTRACT (year FROM Story.date_of_birth) = ?3", nativeQuery = true)
	List<Story> findStoriesByDobFull(int d, int m, int y);
	
	// find story by month and year date of birth
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT (month FROM Story.date_of_birth) = ?1 AND EXTRACT (year FROM Story.date_of_birth) = ?2", nativeQuery = true)
	List<Story> findStoriesByDobYearMonth(int m, int y);
	
	// find story by year date of birth
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT (year FROM Story.date_of_birth) = ?1", nativeQuery = true)
	List<Story> findStoriesByDobYear(int y);
	
	// find story by full date date of death
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT (day FROM Story.date_of_death) = ?1  AND EXTRACT (month FROM Story.date_of_death) = ?2 AND EXTRACT (year FROM Story.date_of_death) = ?3", nativeQuery = true)
	List<Story> findStoriesByDodFull(int d, int m, int y);
	
	// find story by month and year date of death
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT (month FROM Story.date_of_death) = ?1 AND EXTRACT (year FROM Story.date_of_death) = ?2", nativeQuery = true)
	List<Story> findStoriesByDodYearMonth(int m, int y);
	
	// find story by year date of death
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT (year FROM Story.date_of_death) = ?1", nativeQuery = true)
	List<Story> findStoriesByDodYear(int y);
	
	// find story by date of birth and by name of the story
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT  (day FROM Story.date_of_birth) = ?1 AND EXTRACT (month FROM Story.date_of_birth) = ?2 AND EXTRACT (year FROM Story.date_of_birth) = ?3 AND Story.name_of_person LIKE CONCAT('%',?4,'%')", nativeQuery = true)
	List<Story> findStoriesByDateOfBirth(int d ,int m ,int y,String name_of_person);
		
	// find story by date of death and by name of the story
	@Query(value = "SELECT Story.* FROM Story WHERE EXTRACT  (day FROM Story.date_of_death) = ?1 AND EXTRACT (month FROM Story.date_of_death) = ?2 AND EXTRACT (year FROM Story.date_of_death) = ?3 AND Story.name_of_person LIKE CONCAT('%',?4,'%')", nativeQuery = true)
	List<Story> findStoriesByDateOfDeath(int d ,int m ,int y,String name_of_person);
		
}
