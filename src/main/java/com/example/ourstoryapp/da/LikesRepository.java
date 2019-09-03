package com.example.ourstoryapp.da;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Likes;


@RepositoryRestResource
public interface LikesRepository extends JpaRepository <Likes,Long> {

	

}
