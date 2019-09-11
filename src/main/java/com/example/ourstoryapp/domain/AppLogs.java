package com.example.ourstoryapp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppLogs {
	@Id
	private Date time;
	private String sourceClass;
	private String description;
	private String status;
	private String variables;

	public AppLogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppLogs(Date time, String sourceClass, String description, String status, String variables) {
		super();
		this.time = time;
		this.sourceClass = sourceClass;
		this.description = description;
		this.status = status;
		this.variables = variables;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(String sourceClass) {
		this.sourceClass = sourceClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}

}
