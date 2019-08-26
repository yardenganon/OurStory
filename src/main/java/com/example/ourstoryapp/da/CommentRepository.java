package com.example.ourstoryapp.da;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ourstoryapp.domain.Comment;

public interface CommentRepository extends JpaRepository <Comment,Long> {

}
