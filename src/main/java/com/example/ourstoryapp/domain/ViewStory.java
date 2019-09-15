package com.example.ourstoryapp.domain;

import java.util.List;

public class ViewStory {

	Story story;
	List<String> top3tags;
	List<ViewStoryObject> memories;

	public ViewStory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewStory(Story story, List<String> top3tags, List<ViewStoryObject> memories) {
		super();
		this.story = story;
		this.top3tags = top3tags;
		this.memories = memories;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public List<String> getTop3tags() {
		return top3tags;
	}

	public void setTop3tags(List<String> top3tags) {
		this.top3tags = top3tags;
	}

	public List<ViewStoryObject> getMemories() {
		return memories;
	}

	public void setMemories(List<ViewStoryObject> memories) {
		this.memories = memories;
	}

}
