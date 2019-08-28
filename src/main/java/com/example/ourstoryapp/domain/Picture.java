package com.example.ourstoryapp.domain;

import java.net.URI;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Picture {
	
	@Id
	private URI link;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memory")
	private Memory memory;
	
	
	public Picture() {

	}

	public Picture(URI link, Memory memory) {
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
