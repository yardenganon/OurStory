package com.example.ourstoryapp.da;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ourstoryapp.domain.Tag;

public interface MemTagRepository extends JpaRepository <Tag,String>{

}
