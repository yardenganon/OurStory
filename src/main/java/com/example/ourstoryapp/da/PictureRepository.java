package com.example.ourstoryapp.da;

import java.net.URI;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ourstoryapp.domain.Picture;

public interface PictureRepository extends JpaRepository <Picture,URI>{

}
