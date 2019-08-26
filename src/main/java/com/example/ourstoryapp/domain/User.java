package com.example.ourstoryapp.domain;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
@Entity
public class User {
    
      @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private String email;
        private int user_id;
        private String first_name;
        private String last_name;
        private String password;
        private String profile_picture;
        private String gender;
        @Past
        private Date date_of_birth;
        private Date date_of_sign_up;
        private Date date_of_last_sign_in;
        private String state;
        private String city;
        private boolean status;
        

        public User()
        {
        }
        public User(int user_id, String first_name, String last_name, String password, String email, String profile_picture, String gender, Date date_of_birth, Date date_of_sign_up, Date date_of_last_sign_in, String state, String city, boolean status) {
            this.user_id = user_id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.password = password;
            this.email = email;
            this.profile_picture = profile_picture;
            this.gender = gender;
            this.date_of_birth = date_of_birth;
            this.date_of_sign_up = date_of_sign_up;
            this.date_of_last_sign_in = date_of_last_sign_in;
            this.state = state;
            this.city = city;
            this.status = status;
            //this.comments = comments;
        }
        public int getUser_id() {
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
        public String getProfile_picture() {
            return profile_picture;
        }
        public void setProfile_picture(String profile_picture) {
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
}
