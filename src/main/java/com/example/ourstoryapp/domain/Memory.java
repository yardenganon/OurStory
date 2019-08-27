package com.example.ourstoryapp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.net.URI;
import java.util.ArrayList;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Memory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memory_id;

	@ManyToOne
	@JoinColumn(name = "story")
	private Story story;

	@ManyToOne
	@JoinColumn(name = "creator")
	private User creator;

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

	private boolean is_private;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tag_in_memory", joinColumns = @JoinColumn(name = "memory_id", referencedColumnName = "memory_id"), inverseJoinColumns = @JoinColumn(name = "tag_name", referencedColumnName = "tag_name"))
	private Set<Tag> tags;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memory")
	private List<Likes> likes;


	public Memory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Memory(Story storyid, String dec, Date datecreated) {
		this.story = storyid;
		this.description = dec;
		this.create_date = datecreated;

	}

	public long getId() {
		return memory_id;
	}

	public void setId(long id) {
		this.memory_id = id;
	}

	public Story getStory_id() {
		return story;
	}

	public void setStory_id(Story story_id) {
		this.story = story_id;
	}

	public User getCreator_id() {
		return creator;
	}

	public void setCreator_id(User creator_id) {
		this.creator = creator_id;
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

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public boolean isIs_private() {
		return is_private;
	}

	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
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