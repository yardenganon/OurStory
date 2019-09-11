package com.example.ourstoryapp.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Memory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memory_id;

	@ManyToOne
	@JoinColumn(name = "story")
	private Story story;

	@ManyToOne
	@JoinColumn(name = "contributer")
	private User contributer;

	private String description;
	private Date memory_date;
	private Date create_date;
	private String feeling;
	private String location;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memory")
	private List<Comment> comments;

	private boolean is_private;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tag_in_memory", joinColumns = @JoinColumn(name = "memory_id", referencedColumnName = "memory_id"), inverseJoinColumns = @JoinColumn(name = "tag_name", referencedColumnName = "tag_name"))
	@JsonIgnore
	private Set<Tag> tags;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memory")
	@JsonIgnore
	private List<Likes> likes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memory")
	@JsonIgnore
	private List<Picture> pictures;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memory")
	@JsonIgnore
	private List<Video> videos;

	public Memory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Memory(User contributer, Date memory_date, Set<Tag> tags, Story story,String description) {
		super();
		this.contributer = contributer;
		this.memory_date = memory_date;
		this.tags = tags;
		this.story = story;
		this.description = description;
		this.create_date = new Date();

	}


	public Memory(Story story, User contributer, String description, Date memory_date, Date create_date, String feeling,
			String location, List<Picture> pictures) {
		super();
		this.story = story;
		this.contributer = contributer;
		this.description = description;
		this.memory_date = memory_date;
		this.create_date = new Date();
		this.feeling = feeling;
		this.location = location;
		if (pictures != null)
			this.pictures = pictures;
	}

	public long getMemory_id() {
		return memory_id;
	}

	public void setMemory_id(long memory_id) {
		this.memory_id = memory_id;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public User getContributer() {
		return contributer;
	}

	public void setContributer(User contributer) {
		this.contributer = contributer;
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

	public String getFeeling() {
		return feeling;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public boolean isIs_private() {
		return is_private;
	}

	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (memory_id ^ (memory_id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Memory other = (Memory) obj;
		if (memory_id != other.memory_id)
			return false;
		return true;
	}

}