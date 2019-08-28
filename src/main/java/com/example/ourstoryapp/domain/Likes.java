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
	private int like_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memory")
	private Memory memory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "users")
	private User users;

	
	
	
	
	public Likes(Memory memory, User users) {
		super();
		this.memory = memory;
		this.users = users;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public User getUser() {
		return users;
	}

	public void setUser(User users) {
		this.users = users;
	}
	
	
	
}
