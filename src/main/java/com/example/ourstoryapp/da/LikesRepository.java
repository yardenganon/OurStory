package com.example.ourstoryapp.da;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.ourstoryapp.domain.Likes;


public interface LikesRepository extends JpaRepository <Likes,Long> {

}
