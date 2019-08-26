package com.example.ourstoryapp.da;

import org.springframework.data.repository.CrudRepository;
import com.example.ourstoryapp.domain.Story;

public interface StoryRepository extends CrudRepository <Story ,Long> { 

}
