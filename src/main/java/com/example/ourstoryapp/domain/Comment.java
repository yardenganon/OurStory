package com.example.ourstoryapp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memory")
	private Memory memory;
	
	private Date createDate;
	
	//private User user;
	private String text;
	

	
	

	public Comment() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Memory memory, Date createDate, User user, String text) {
		super();
		this.memory = memory;
		this.createDate = createDate;
//		this.user = user;
		this.text = text;
	}
	public Comment(Memory memory, Date d2, String text) {
		super();
		this.memory = memory;
		this.createDate = d2;
//		this.user = user;
		this.text = text;
	}
	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
