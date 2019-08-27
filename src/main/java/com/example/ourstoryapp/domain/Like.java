package com.example.ourstoryapp.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Like implements Serializable {
	
	@Id
	@OneToOne
	@JoinColumn(name = "user_liked")
	private User user_liked;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memory")
	private Memory memory;

	public User getUser() {
		return user_liked;
	}

	public void setUser(User user) {
		this.user_liked = user;
	}

	public Like(User user) {
		super();
		this.user_liked = user;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}
}
