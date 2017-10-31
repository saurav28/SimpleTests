package org.saurav.simpletests.reactive;

import java.time.LocalDate;

public class Person {
	
	  public Person(String name,int age,Sex gender){
		  this.name = name;
		  this.age = age;
		  this.gender = gender;
	  }
	  public enum Sex {
	        MALE, FEMALE
	    }

	  	int age;
	    String name;
	    LocalDate birthday;
	    Sex gender;
	    String emailAddress;

	    public Sex getGender() {
	    	return gender;
	    }
	    public int getAge() {
	       return age;
	    }

	    public void printPerson() {
	       System.out.println("person name ::" +name);
	    }

}
