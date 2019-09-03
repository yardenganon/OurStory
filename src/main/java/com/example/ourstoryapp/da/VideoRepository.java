package com.example.ourstoryapp.da;

import java.net.URI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ourstoryapp.domain.Video;
@RepositoryRestResource
public interface VideoRepository extends JpaRepository <Video,URI>{

	
	
	
}
