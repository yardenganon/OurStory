package com.example.ourstoryapp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
	@Id
	String tag_name;

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
