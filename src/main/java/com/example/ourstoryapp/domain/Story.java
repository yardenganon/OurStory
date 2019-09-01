package com.example.ourstoryapp.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long story_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner")
	private User owner;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "story")
	@JsonIgnore
	private List<Memory> memories;
	private String name_of_person;
	private Date date_of_birth, date_of_death;
	private URI picture;

	public Story(User owner, String name_of_person, Date date_of_birth, Date date_of_death, URI picture) {
		super();
		this.owner = owner;
		this.name_of_person = name_of_person;
		this.date_of_birth = date_of_birth;
		this.date_of_death = date_of_death;
		if (picture != null)
			this.picture = picture;

	}

	public Story() {
		// TODO Auto-generated constructor stub
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Memory> getMemories() {
		return memories;
	}

	public void setMemories(List<Memory> memories) {
		this.memories = memories;
	}

	public String getName_of_person() {
		return name_of_person;
	}

	public void setName_of_person(String name_of_person) {
		this.name_of_person = name_of_person;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public Date getDate_of_death() {
		return date_of_death;
	}

	public void setDate_of_death(Date date_of_death) {
		this.date_of_death = date_of_death;
	}

	public URI getPicture() {
		return picture;
	}

	public void setPicture(URI picture) {
		this.picture = picture;
	}

	public long getStory_id() {
		return story_id;
	}

	public void setStory_id(long story_id) {
		this.story_id = story_id;
	}

}
