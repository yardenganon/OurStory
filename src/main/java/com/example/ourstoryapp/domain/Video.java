package com.example.ourstoryapp.domain;

import java.net.URI;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Video {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memory")
	private Memory memory;
	
	private URI link;
	
	
	public Video() {

	}

	public Video(URI link, Memory memory) {
		super();
		this.link = link;
		this.memory = memory;
	}

	public URI getLink() {
		return link;
	}

	public void setLink(URI link) {
		this.link = link;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	
	
	
	

}
