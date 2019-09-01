package com.example.ourstoryapp.da;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ourstoryapp.domain.Memory;
import com.example.ourstoryapp.domain.Tag;

@RepositoryRestResource
public interface MemoryRepository extends JpaRepository<Memory, Long> {

	@Query(value = "SELECT * FROM MEMORY WHERE Memory.contributer = ?1 ORDER BY Memory.create_date DESC", nativeQuery = true)
	List<Memory> getUserMemories(long userID);

	@Query(value = "SELECT * FROM MEMORY WHERE (Memory.story=?1 AND EXTRACT (year FROM Memory.memory_date) = ?2 ) ORDER BY Memory.memory_date DESC", nativeQuery = true)
	List<Memory> findMemoriesByYear(long story, int year);

	@Query(value = "SELECT MEMORY.* FROM TAG_IN_MEMORY INNER JOIN MEMORY on (MEMORY.memory_id=TAG_IN_MEMORY.memory_id) WHERE (Memory.story=?1 AND TAG_IN_MEMORY.tag_name=?2) ORDER BY Memory.memory_date DESC", nativeQuery = true)
	List<Memory> findMemoriesByTag(long story, String t);

	@Query(value = "SELECT MEMORY.* FROM Memory inner join Story on  Memory.story=Story.story_id WHERE (Memory.description LIKE CONCAT('%',?1,'%') OR Story.name_of_person LIKE CONCAT('%',?1,'%') OR Memory.location LIKE CONCAT('%',?1,'%')) ORDER BY Memory.memory_date DESC", nativeQuery = true)
	List<Memory> findMemoriesByKeyword(String desc);

}
 