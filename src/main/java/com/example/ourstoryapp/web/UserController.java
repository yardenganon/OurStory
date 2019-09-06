package com.example.ourstoryapp.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.example.ourstoryapp.service.LoggingController;

@RestController
@RequestMapping("/users")
public class UserController { 
	
    @Autowired
	private UserRepository repository;
    Logger logger = LogManager.getLogger(LoggingController.class);
    

	// get all users - sorted by ID (Read)
    @GetMapping("/findAll")
    public Iterable<User> getUser() {
    	logger.info("Find All Users");
    	return repository.findAll();
    }
    
    // get user by ID (Read)
 	@GetMapping("/findById/{id}")
 	public ResponseEntity<User> findById(@PathVariable(value = "id") long userId) {
 		if((repository.findById(userId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
 			logger.info("Find User By ID");
 			return repository.findById(userId).map(record -> ResponseEntity.ok().body(record))
 					.orElse(ResponseEntity.notFound().build());
 		}
 		else {
 			logger.info("User By ID Is Not Found");
 			return ResponseEntity.notFound().build();
 		}
 	}
 	
 	
 	// get user by Email
 	@GetMapping("/findByEmail/{email}")
 	public List<User> findByEmail(@PathVariable(value = "email") String userEmail) {
 		if((repository.findByEmail(userEmail)).size()>0) {
 			logger.info("Find By Email");
 	 		return repository.findByEmail(userEmail);
 		}
 		else{
 			logger.info("Find By Email is not found");
 			return null;
 		}
 	}
 
    // create new instance of User (Create)
 	@PostMapping("/create")
 	public User create(@Valid @RequestBody User user) {
 		logger.info("Cretae User");
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
		if((repository.findById(userId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
			repository.deleteById(userId);
			logger.info("Successfully deleted User");
			return ResponseEntity.ok().build();
		}
		
		else {
			logger.info("User is not existing");
			return ResponseEntity.notFound().build();
		}
	}

}

