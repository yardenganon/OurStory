package com.example.ourstoryapp.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Likes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int like_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memory")
	@JsonIgnore
	private Memory memory;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "users")
	@JsonIgnore
	private User users;

	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Likes(Memory memory, User user) {
		super();
		this.memory = memory;
		this.users = user;
	}

	public int getLike_id() {
		return like_id;
	}

	public void setLike_id(int like_id) {
		this.like_id = like_id;
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

	public void setUser(User user) {
		this.users = user;
	}

	@Override
	public String toString() {
		return "Likes [like_id=" + like_id + ", memoryId=" + memory.getMemory_id() + "]";
	}

}
