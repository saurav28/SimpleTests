package org.saurav.simpletests.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class EliminateDuplicate {
	
	public static void main(String a[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayList<String> inputs = new ArrayList<String>(); 
        Integer numOflines = Integer.parseInt(line);
        if(numOflines >100 || numOflines <1){
        	return ;
        }
        for (int i = 0; i < numOflines; i++) {
			String input  = br.readLine();
			inputs.add(input);			
		}
        for (Iterator iterator = inputs.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if(string.length() >1000000){
				continue;
			}
			string = eliminateStringDuplicate(string);
			System.out.println(string);
		}
	}
	
	public static String eliminateStringDuplicate(String input){
		char[] chars = input.toCharArray();
		StringBuffer newString = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			if(i==0){
				newString.append(chars[i]);
			}
			else if(!(chars[i-1]==chars[i])){
				newString.append(chars[i]);
			}
		}
		return newString.toString();
	}

}
