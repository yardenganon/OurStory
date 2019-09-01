package com.example.ourstoryapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.UserRepository;
import com.example.ourstoryapp.domain.User;

@RestController
@RequestMapping("/users")
public class UserController { 
	
    @Autowired
	private UserRepository repository;
    

	// get all users - sorted by ID (Read)
    @GetMapping("/findAll")
    public Iterable<User> getUser() {
      return repository.findAll();
    }
    
    // get user by ID (Read)
 	@GetMapping("/findById/{id}")
 	public ResponseEntity<User> findById(@PathVariable(value = "id") long userId) {
 		return repository.findById(userId).map(record -> ResponseEntity.ok().body(record))
 				.orElse(ResponseEntity.notFound().build());
 	}
 	
    // create new instance of User (Create)
 	@PostMapping("/create")
 	public User create(@Valid @RequestBody User user) {
 		return repository.save(user);
 	}
 	
	// update story by id using new values (Update)
//	@PutMapping(value="/{id}")
//	public ResponseEntity<User> update(@PathVariable("id") long userId,
//  	                                        @RequestBody User user){
//		    return repository.findById(userId)
//		        .map(record -> {
//		        	record.setFirst_name(user.getFirst_name());
//		        	record.setLast_name(user.getLast_name());
//		            record.setEmail(user.getEmail());
//		            record.setProfile_picture(user.getProfile_picture());
//		            record.setGender(user.getGender());
//		            record.setState(user.getState());
//		            record.setCity(user.getCity());
//		            record.setStatus(user.isStatus());
//		            record.setPassword(user.getPassword());
//		            record.setDate_of_last_sign_in(user.getDate_of_last_sign_in());
//		            User updated = repository.save(record);
//		            return ResponseEntity.ok().body(updated);
//		        }).orElse(ResponseEntity.notFound().build());
//	}     
 	
	// delete story by ID (Delete)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long userId) {
		return repository.findById(userId).map(record -> {
			repository.deleteById(userId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}

