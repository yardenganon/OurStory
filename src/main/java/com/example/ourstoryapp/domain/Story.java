package com.example.ourstoryapp.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Story{
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private long Story_ID;
   private long Owner_ID;
   private String Date_Of_Birth, Date_Of_Death,Name_Of_Person;
   private String picture;
   //ArrayList<Memory> memorys;
   public Story(long story_ID, long owner_ID, String date_Of_Birth, String date_Of_Death, String name_Of_Person, String picture) {
       Story_ID = story_ID;
       Owner_ID = owner_ID;
       Date_Of_Birth = date_Of_Birth;
       Date_Of_Death = date_Of_Death;
       Name_Of_Person = name_Of_Person;
       this.picture = picture;
   }
  /* public ArrayList<Memory> getMemorys(){
       return memorys;
   }*/
   public long getStory_ID() {
       return Story_ID;
   }
   public void setStory_ID(long story_ID) {
       Story_ID = story_ID;
   }
   public long getOwner_ID() {
       return Owner_ID;
   }
   public void setOwner_ID(long owner_ID) {
       Owner_ID = owner_ID;
   }
   public String getDate_Of_Birth() {
       return Date_Of_Birth;
   }
   public void setDate_Of_Birth(String date_Of_Birth) {
       Date_Of_Birth = date_Of_Birth;
   }
   public String getDate_Of_Death() {
       return Date_Of_Death;
   }
   public void setDate_Of_Death(String date_Of_Death) {
       Date_Of_Death = date_Of_Death;
   }
   public String getName_Of_Person() {
       return Name_Of_Person;
   }
   public void setName_Of_Person(String name_Of_Person) {
       Name_Of_Person = name_Of_Person;
   }
   public String getPicture() {
       return picture;
   }
   public void setPicture(String picture) {
       this.picture = picture;
   }
}
