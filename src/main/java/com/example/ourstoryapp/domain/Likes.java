package com.example.ourstoryapp.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Likes implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memory")
	private Memory memory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;

	
	
	
	
	public Likes(Memory memory, User user) {
		super();
		this.memory = memory;
		this.user = user;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
