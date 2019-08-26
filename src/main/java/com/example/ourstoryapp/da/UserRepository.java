package com.example.ourstoryapp.da;

import org.springframework.data.repository.CrudRepository;
import com.example.ourstoryapp.domain.User;


 public interface UserRepository extends CrudRepository <User,Long> {

}
