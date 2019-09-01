package com.example.ourstoryapp.da;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Tag;


@RepositoryRestResource
public interface MemTagRepository extends CrudRepository <Tag,String>{

}
