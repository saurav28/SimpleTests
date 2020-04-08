package org.saurav.simpletests.collections;

import java.util.HashMap;

public class HashMapTester {
	
	private static long[] longA = {4,5,6,7};
	private static HashMap<long[], String> objectMapper = new HashMap<long[], String>();

	public static void main (String a[]){
		testObjectMapper();
		
	}

	private static void testObjectMapper() {
		// TODO Auto-generated method stub
		System.out.println("Current working directory ::" + System.getProperty("user.dir"));
		objectMapper.put(longA, "haha");
		
		System.out.println("mapped value" + objectMapper.get(longA));
		
	}
}
