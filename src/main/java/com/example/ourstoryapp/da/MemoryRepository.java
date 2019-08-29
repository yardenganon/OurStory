package com.example.ourstoryapp.da;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ourstoryapp.domain.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

}
