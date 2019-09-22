package com.example.ourstoryapp.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;

	private String email;

	private String first_name;
	private String last_name;
	private String password;
	private String gender;

	private Date date_of_birth;
	private Date date_of_sign_up;
	private Date date_of_last_sign_in;
	private String state;
	private String city;
	private boolean status;

	private URI profile_picture;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private List<Comment> comments;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private List<Likes> likes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contributer")
	private List<Memory> memories;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<Story> owners;

	public User() {

	}
	

	
	public User(String first_name, String last_name, String password) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.date_of_sign_up = new Date();

	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public URI getProfile_picture() {
		return profile_picture;
	}

	public void setProfile_picture(URI profile_picture) {
		this.profile_picture = profile_picture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public Date getDate_of_sign_up() {
		return date_of_sign_up;
	}

	public void setDate_of_sign_up(Date date_of_sign_up) {
		this.date_of_sign_up = date_of_sign_up;
	}

	public Date getDate_of_last_sign_in() {
		return date_of_last_sign_in;
	}

	public void setDate_of_last_sign_in(Date date_of_last_sign_in) {
		this.date_of_last_sign_in = date_of_last_sign_in;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (user_id != other.user_id)
			return false;
		return true;
	}
}
