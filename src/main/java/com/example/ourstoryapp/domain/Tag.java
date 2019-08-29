package com.example.ourstoryapp.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String tag_name;
	
	@ManyToMany(mappedBy = "tags")
	 @JsonManagedReference
	private Set<Memory> memories;

	@ManyToMany(mappedBy = "tags")
	 @JsonManagedReference
	private Set<Comment> comments;

	public Set<Memory> getMemories() {
		return memories;
	}

	public void setMemories(Set<Memory> memories) {
		this.memories = memories;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String tag_name) {
		super();
		this.tag_name = tag_name;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tag_name == null) ? 0 : tag_name.hashCode());
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
		Tag other = (Tag) obj;
		if (tag_name == null) {
			if (other.tag_name != null)
				return false;
		} else if (!tag_name.equals(other.tag_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [tag_name=" + tag_name + "]";
	}

}
