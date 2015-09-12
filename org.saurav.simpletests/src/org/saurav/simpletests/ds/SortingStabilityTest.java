package org.saurav.simpletests.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class SortingStabilityTest {

	public static void main (String a[]){
		SortingStabilityTest st = new SortingStabilityTest();
		st.sortNames();
	}
	
	private void sortNames(){
		ArrayList<Person> PersonList = new ArrayList<Person>();
		Person A = new Person("Saurav","Sarkar");
		Person B = new Person("Ishant", "Sharma");
		Person C= new Person ("Rohit", "Sharma");
		PersonList.add(A);
		PersonList.add(B);
		PersonList.add(C);
		display(PersonList);
	}
	
	private void display(ArrayList<Person> personList){
		Iterator<Person> iterator = personList.iterator();
		
		
		System.out.println(personList);
	}
	
}


class Person{
	private String firstName;
	private String lastName;
	
	Person(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
}

