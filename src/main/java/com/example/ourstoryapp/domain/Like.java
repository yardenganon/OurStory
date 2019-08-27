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
	@ManyToOne // (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn // (name = "user_liked")
	User user_liked;

	@Id
	@ManyToOne // (fetch = FetchType.EAGER)
	@JoinColumn // (name = "memory")
	Memory memory;

	public User getUser_liked() {
		return user_liked;
	}

	public void setUser_liked(User user_liked) {
		this.user_liked = user_liked;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	private boolean isLiked;

	public Like() {
		super();
	}

	public Like(User user_liked) {
		super();
		this.user_liked = user_liked;
	}

	public Like(User user_liked, Memory memory) {
		super();
		this.user_liked = user_liked;
		this.memory = memory;
	}

	public User getUser() {
		return user_liked;
	}

	public void setUser(User user) {
		this.user_liked = user;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}
}
