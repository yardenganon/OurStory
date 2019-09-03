package com.example.ourstoryapp.da;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Comment;
import com.example.ourstoryapp.domain.Tag;


@RepositoryRestResource
public interface MemTagRepository extends CrudRepository <Tag,String>{
	

	
	

}
