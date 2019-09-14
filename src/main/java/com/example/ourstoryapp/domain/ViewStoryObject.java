package com.example.ourstoryapp.domain;

import java.util.List;

public class ViewStoryObject {
	private int year;
	private List<String[]> pics;

	public ViewStoryObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewStoryObject(int year) {
		super();
		this.year = year;
	}

	public ViewStoryObject(int year, List<String[]> pics) {
		super();
		this.year = year;
		this.pics = pics;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String[]> getPics() {
		return pics;
	}

	public void setPics(List<String[]> pics) {
		this.pics = pics;
	}

}
