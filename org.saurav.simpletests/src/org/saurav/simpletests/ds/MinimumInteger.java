package org.saurav.simpletests.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Codlity Minimum integer demo test
 * @author Saurav Sarkar
 *
 */
public class MinimumInteger {
	
	public static void main(String a[]) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] values;
		try {
			values = br.readLine().split(" ");
		
		int[] valuesarray = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			valuesarray[i] = Integer.parseInt(values[i]);
		}
		
		int minElement = findMinInteger(valuesarray);
		System.out.println(minElement);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int findMinInteger(int[] A) {
		int N = A.length;
		int minElement = 1;
		boolean found = false;
		Set<Integer> set = new HashSet<>();
		for (int a : A) {
		    if (a > 0) {
		        set.add(a);
		    }
		}
		for (int i = 1; i <= N + 1 && !found; i++) {
		    if (!set.contains(i)) {
		    	found = true;
		        minElement = i;
		    }
		}
		return minElement;
	}

}
