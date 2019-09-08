package com.example.ourstoryapp.web;

import java.util.Random;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.da.UserRepository;
import com.example.ourstoryapp.domain.User;
import com.example.ourstoryapp.mail.MailClient;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private MailClient mailClient;

	// get all users - sorted by ID (Read)
	@GetMapping("/findAll")
	public Iterable<User> getUser() {
		logger.info("Find All Users");
		return repository.findAll();
	}

	// get user by ID (Read)
	@GetMapping("/findById/{id}")
	public ResponseEntity<User> findById(@PathVariable(value = "id") long userId) {
		if ((repository.findById(userId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
			logger.info("Find User By ID");
			return repository.findById(userId).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		} else {
			logger.info("User By ID Is Not Found");
			return ResponseEntity.notFound().build();
		}
	}

	// get user by Email
	@GetMapping("/findByEmail")
	public User findByEmail(@RequestParam("mail") String mail) {
		if ((repository.findByEmail(mail)) != null) {
			logger.info("Find By Email");
			return repository.findByEmail(mail);
		} else {
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

	@PutMapping(value = "/updatePassword/{id}")
	public ResponseEntity<User> updatePassword(@PathVariable("id") long id) {
		return repository.findById(id).map(record -> {
			String password = new Random().ints(10, 33, 122).mapToObj(i -> String.valueOf((char) i))
					.collect(Collectors.joining());
			record.setPassword(password);
			User updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	// delete story by ID (Delete)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long userId) {
		if ((repository.findById(userId).map(record -> ResponseEntity.ok().body(record))).isPresent()) {
			repository.deleteById(userId);
			logger.info("Successfully deleted User");
			return ResponseEntity.ok().build();
		}

		else {
			logger.info("User is not existing");
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public void resetPassword(@RequestParam("mail") String mail) {
		User u = findByEmail(mail);
		updatePassword(u.getUser_id());
		u = findByEmail(mail);
		//don't forget to update to mail parameter
		String recipient = "frouhana@outlook.com";
		String message = u.getPassword();
		mailClient.prepareAndSend(recipient, message);
	}

}
