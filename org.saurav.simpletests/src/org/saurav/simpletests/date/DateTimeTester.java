package org.saurav.simpletests.date;

import java.time.Instant;
/**
 * Basic class to test the data time functionality of Java
 * @author Saurav Sarkar
 *
 */
public class DateTimeTester {
	
	public static void main (String a[]) {
		
		System.out.println("Current time " + Instant.now());  // get the current time
		
		System.out.println("Current time " + Instant.now().toEpochMilli());  // get the epoch second
		
		DateTimeTester tester = new DateTimeTester();
		tester.convertToEpoch("2020-07-10T00:00:00.000Z");
		
	}
	
	public void convertToEpoch(String date) {
		System.out.println("Get epoch second of the data " + Instant.parse(date).toEpochMilli());
	}

}
