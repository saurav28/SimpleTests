package org.saurav.simpletests.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringReverse {
	
	public static void main (String a[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = br.readLine();
		reverseStringRecursively(string.substring(1), string.charAt(0));
	}
	
	public static String reverseStringRecursively(String st,char a) {
		if("".equals(st)){
			System.out.print(a);
			return st;
		}else{
			
			reverseStringRecursively(st.substring(1), st.charAt(0));
			System.out.print(a);
			return st;
		}
	}

}
