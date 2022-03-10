package org.saurav.simpletests.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Set of Array problems clubbed into class
 * 
 * 1. Print Unique elements in an array - Done with hashing
 * @author Saurav Sarkar
 *
 */
public class ArrayProblems {

	public static void main(String a[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] values = br.readLine().split(" ");
		int[] valuesarray = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			valuesarray[i] = Integer.parseInt(values[i]);
		}
		
		ArrayProblems.findDistinctElements(valuesarray);
	}

	private static void findDistinctElements(int[] valuearray) {

		HashSet<Integer> setofIntegers = new HashSet<Integer>();
		for (int i = 0; i < valuearray.length; i++) {

			setofIntegers.add(valuearray[i]);

		}
		
		System.out.println("Printing the unique elements");
		for (Iterator iterator = setofIntegers.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.print(integer + " ");

		}

	}

}
