package com.example.ourstoryapp.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Story_ID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner")
	private User owner;
	
	
	
	private String Date_Of_Birth, Date_Of_Death, Name_Of_Person;
	private String picture;
	

	public Story(long story_ID, User owner_ID, String date_Of_Birth, String date_Of_Death, String name_Of_Person,
			String picture, User user) {
		Story_ID = story_ID;
		owner = owner_ID;
		Date_Of_Birth = date_Of_Birth;
		Date_Of_Death = date_Of_Death;
		Name_Of_Person = name_Of_Person;
		this.picture = picture;
		//this.user = user;
	}

//	public User getUser() {
//		return user;
//	}

//	public void setUser(User user) {
//		this.user = user;
//	}

	/*
	 * public ArrayList<Memory> getMemorys(){ return memorys; }
	 */
	public long getStory_ID() {
		return Story_ID;
	}

	public void setStory_ID(long story_ID) {
		Story_ID = story_ID;
	}

	public User getOwner_ID() {
		return owner;
	}

	public void setOwner_ID(User owner_ID) {
		owner = owner_ID;
	}

	public String getDate_Of_Birth() {
		return Date_Of_Birth;
	}

	public void setDate_Of_Birth(String date_Of_Birth) {
		Date_Of_Birth = date_Of_Birth;
	}

	public String getDate_Of_Death() {
		return Date_Of_Death;
	}

	public void setDate_Of_Death(String date_Of_Death) {
		Date_Of_Death = date_Of_Death;
	}

	public String getName_Of_Person() {
		return Name_Of_Person;
	}

	public void setName_Of_Person(String name_Of_Person) {
		Name_Of_Person = name_Of_Person;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
