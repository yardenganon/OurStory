package com.example.ourstoryapp.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Like implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
//	@ManyToOne
//	@JoinColumn 
//	User user;

	
	@ManyToOne 
	@JoinColumn
	Memory memory;
	
	public Like() {
		super();
	}

	public Like(boolean isLiked, User user_liked) {
		super();
//		this.user = user_liked;
		this.isLiked = isLiked;
	}
	
//	public User getUser_liked() {
//		return user;
//	}
//
//	public void setUser_liked(User user_liked) {
//		this.user = user_liked;
//	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	private boolean isLiked;






//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}
}
