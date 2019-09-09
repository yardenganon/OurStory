package com.example.ourstoryapp.da;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ourstoryapp.domain.AppLogs;


public interface LogRepository extends JpaRepository<AppLogs, Date>  {

}
