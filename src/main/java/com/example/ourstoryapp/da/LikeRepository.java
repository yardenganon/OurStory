package com.example.ourstoryapp.da;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ourstoryapp.domain.Like;

public interface LikeRepository extends JpaRepository <Like,Long> {

}
