package com.example.ourstoryapp.da;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.User;

@RepositoryRestResource
 public interface UserRepository extends CrudRepository <User,Long> {

}
