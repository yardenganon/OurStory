package com.example.ourstoryapp.da;

import java.net.URI;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.ourstoryapp.domain.Video;

public interface VideoRepository extends JpaRepository <Video,URI>{

}
