package com.example.ourstoryapp.mail;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ourstoryapp.domain.User;
import com.example.ourstoryapp.web.UserController;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	public EmailServiceImpl emailService;
	public UserController c;

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String createMail(@PathVariable("email") String email) {
		User u = c.findByEmail(email);
		c.updatePassword(u.getUser_id());
		u = c.findByEmail(email);
		emailService.sendSimpleMessage("frouhana@outlook.com", "Forgot Password", u.getPassword());
		return "redirect:/home";
	}

}