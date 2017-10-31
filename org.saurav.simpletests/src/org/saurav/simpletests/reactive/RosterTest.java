package org.saurav.simpletests.reactive;

import java.util.ArrayList;
import java.util.List;

import org.saurav.simpletests.reactive.Person.Sex;
/**
 * 
 * @author Saurav Sarkar
 * This is a test code for learning lambda expressions in Java
 * Check here https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#use-case
 *
 */
public class RosterTest {
	
	static List<Person> personList = new ArrayList<Person>();
	
	public static void main (String a[]){
		loadData();
		printPersonsOlderThan(personList, (Person p) -> p.getGender() == Person.Sex.MALE
				&& p.getAge() >= 18 
				&& p.getAge() <= 30);
	}
	
	public static void printPersonsOlderThan(List<Person> roster, Predicate<Person> tester) {
	    for (Person p : roster) {
	    	  if (tester.test(p)) {
	              p.printPerson();
	          }
	    }
	}
	
	private static void loadData(){
		Person p1 = new Person("Saurav", 35, Sex.MALE);
		Person p2 = new Person("Tom", 29, Sex.MALE);
		personList.add(p1);
		personList.add(p2);
	}


}
