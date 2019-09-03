package com.example.ourstoryapp.da;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Comment;


@RepositoryRestResource
public interface CommentRepository extends JpaRepository <Comment,Long> {
	
	// get list of comment by Memory ID

}
