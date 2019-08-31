package com.example.ourstoryapp.da;

import org.springframework.data.repository.CrudRepository;

import com.example.ourstoryapp.domain.Tag;

public interface MemTagRepository extends CrudRepository <Tag,String>{

}
