package org.saurav.simpletests.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem for hackerearth august 2018 circuit
 * 
 * 
 * You are given a String  S of size N , consisting of lowercase English characters. Now, you need to select a single English lowercase alphabet, and delete all occurences of it from the given string 
Considering you do the mentioned exactly once over the given string, what is the minimum possible length of the resultant string ?

Input Format :

The first line contains a single integer N. The next line contains a String  of length  consisting of lowercase Englsh characters.

Output Format :

Print the required answer on a single line

Constraints :
1<= N <= 100000


 * @author 
 *
 */
public class StringTest {
	
	HashMap<Character, Integer> countKeys = new HashMap<>();
	int maxCount=1;
	static int strLength;
	static String testString;
	
	public static void main(String a[]) {
		StringTest st = new StringTest();
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 
	        try {
	        		strLength = Integer.parseInt(br.readLine());
	        		if(strLength<1 || strLength >100000) {
	        			System.out.println("invalid input");
	        			return;
	        		}
				testString = br.readLine();
				String regex = ".*[A-Z].*";
				if (testString.matches(regex)) { //checks if the string contains any upper case
				  System.out.println("invalid input");
				  return;
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		
		st.performStringOperation(testString);
		//st.printCharFrequency();
		st.calculateAndPrintLength(testString);
	}
	
	private void performStringOperation(String testString) {
		char[] charArray = testString.toCharArray();
		for (int i = 0; i < strLength; i++) {
			char key = charArray[i];
			if(countKeys.containsKey(key)) {
				int count  = countKeys.get(key);
				count = count +1;
				if(maxCount < count) {
					maxCount = count;
				}
				countKeys.put(key, count);
			}else {
				countKeys.put(key, 1);
			}
		}
		
	}
	
	private void printCharFrequency() {
		for (Map.Entry<Character, Integer> entry : countKeys.entrySet()) {
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		
		System.out.println("Maximum count" + maxCount);
		
	}
	
	private void calculateAndPrintLength(String testString) {
		int shortestStringLenght = strLength - maxCount;
		System.out.println(" Minimum string length" + shortestStringLenght);
		
	}

}
