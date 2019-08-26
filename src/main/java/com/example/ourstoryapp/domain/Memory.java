package com.example.ourstoryapp.domain;


import java.util.Date;
import java.util.List;
import java.net.URI;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Memory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private long story_id;
	private String creator_id;
	private String description;
	private Date memory_date;
	private Date create_date;
	private Feeling feeling;
	private String location;
//	private List<URI> pictures;
//	private List<URI> videos;
//	private List<Tag> tags;
//	private List<User> likes;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memory")
	private List<Comment> comments;
	//private ArrayList<User> shares;
	private boolean is_private;
	
	public Memory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Memory(long story_id, String creator_id, String description, Date memory_date, Date create_date,
			Feeling feeling, String location, ArrayList<URI> pictures, ArrayList<URI> videos, ArrayList<Tag> tags,
			ArrayList<User> likes, ArrayList<Comment> comments, ArrayList<User> shares, boolean is_private) {
		super();
		
		this.story_id = story_id;
		this.creator_id = creator_id;
		this.description = description;
		this.memory_date = memory_date;
		this.create_date = create_date;
		this.feeling = feeling;
		this.location = location;
//		this.pictures = pictures;
//		this.videos = videos;
//		this.tags = tags;
//		this.likes = likes;
		this.comments = comments;
//		this.shares = shares;
		this.is_private = is_private;
	}
	public Memory(int storyid,String dec, Date datecreated)
	{
		this.story_id = storyid;
		this.description = dec;
		this.create_date = datecreated;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStory_id() {
		return story_id;
	}
	public void setStory_id(long story_id) {
		this.story_id = story_id;
	}
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getMemory_date() {
		return memory_date;
	}
	public void setMemory_date(Date memory_date) {
		this.memory_date = memory_date;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Feeling getFeeling() {
		return feeling;
	}
	public void setFeeling(Feeling feeling) {
		this.feeling = feeling;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
//	public ArrayList<URI> getPictures() {
//		return pictures;
//	}
//	public void setPictures(ArrayList<URI> pictures) {
//		this.pictures = pictures;
//	}
//	public ArrayList<URI> getVideos() {
//		return videos;
//	}
//	public void setVideos(ArrayList<URI> videos) {
//		this.videos = videos;
//	}
//	public ArrayList<Tag> getTags() {
//		return tags;
//	}
//	public void setTags(ArrayList<Tag> tags) {
//		this.tags = tags;
//	}
//	public ArrayList<User> getLikes() {
//		return likes;
//	}
//	public void setLikes(ArrayList<User> likes) {
//		this.likes = likes;
//	}
//	public ArrayList<Comment> getComments() {
//		return comments;
//	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
//	public ArrayList<User> getShares() {
//		return shares;
//	}
//	public void setShares(ArrayList<User> shares) {
//		this.shares = shares;
//	}
	public boolean isIs_private() {
		return is_private;
	}
	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}
}