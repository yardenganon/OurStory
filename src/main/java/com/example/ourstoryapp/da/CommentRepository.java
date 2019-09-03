package com.example.ourstoryapp.da;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Comment;



@RepositoryRestResource
public interface CommentRepository extends JpaRepository <Comment,Long> {
	
	// get list of comments by Memory ID
	
	
	@Query(value = "SELECT Comment.* FROM COMMENT WHERE Comment.memory = ?1 ORDER BY Comment.create_date DESC", nativeQuery = true)
	List<Comment> getMemoryComments(long memoryId);

}
