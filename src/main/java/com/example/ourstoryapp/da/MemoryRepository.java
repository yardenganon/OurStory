package com.example.ourstoryapp.da;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Memory;

@RepositoryRestResource
public interface MemoryRepository extends JpaRepository<Memory, Long> {

	@Query(value = "SELECT * FROM MEMORY WHERE Memory.contributer = ?1 ORDER BY Memory.create_date DESC", nativeQuery = true)
	List<Memory> getUserMemories(long userID);

	@Query(value = "SELECT * FROM MEMORY WHERE (Memory.story=?1 AND EXTRACT (year FROM Memory.memory_date) = ?2 ) ORDER BY Memory.memory_date DESC", nativeQuery = true)
	List<Memory> findMemoriesByYear(long story, int year);

	@Query(value = "SELECT * FROM MEMORY WHERE (Memory.story=?1) ORDER BY Memory.memory_date DESC", nativeQuery = true)
	List<Memory> getStoryMemoriesSortedByYear(long story);

	@Query(value = "SELECT MEMORY.* FROM TAG_IN_MEMORY INNER JOIN MEMORY on (MEMORY.memory_id=TAG_IN_MEMORY.memory_id) WHERE (Memory.story=?1 AND TAG_IN_MEMORY.tag_name=?2) ORDER BY Memory.memory_date DESC", nativeQuery = true)
	List<Memory> findMemoriesByTag(long story, String t);

	@Query(value = "SELECT MEMORY.* FROM Memory inner join Story on  Memory.story=Story.story_id WHERE (Memory.description LIKE CONCAT('%',?1,'%') OR Story.name_of_person LIKE CONCAT('%',?1,'%') OR Memory.location LIKE CONCAT('%',?1,'%') OR ( CONCAT(EXTRACT (year FROM Memory.memory_date), '') LIKE CONCAT('%',?1,'%'))) ORDER BY Memory.memory_date DESC", nativeQuery = true)
	List<Memory> findMemoriesByKeyword(String desc);

	@Query(value = "SELECT DISTINCT EXTRACT (year FROM m2.memory_date) FROM Picture p2 inner join Memory m2 on p2.memory = m2.memory_id WHERE m2.story=?1 GROUP BY EXTRACT (year FROM m2.memory_date) HAVING COUNT(*)>=3", nativeQuery = true)
	List<Integer> ViewStoryHelperMethod(long story);

	@Query(value = "SELECT DISTINCT Picture.* FROM Picture inner join Memory on Picture.memory = Memory.memory_id WHERE Memory.story=?1 AND EXTRACT (year FROM Memory.memory_date) =?2 LIMIT 3", nativeQuery = true)
	List<String[]> ViewStory(long story, int year);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM TAG_IN_MEMORY WHERE TAG_IN_MEMORY.memory_id=?1 ", nativeQuery = true)
	void DeleteAllTagsInMemory(long memory);
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM VIDEO WHERE VIDEO.memory=?1 ", nativeQuery = true)
	void DeleteAllPicsInMemory(long memory);
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM PICTURE WHERE PICTURE.memory=?1 ", nativeQuery = true)
	void DeleteAllVideosInMemory(long memory);
	// get memories by words in description of memory

}
