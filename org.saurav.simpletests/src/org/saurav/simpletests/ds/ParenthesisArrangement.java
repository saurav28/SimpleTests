package org.saurav.simpletests.ds;

import java.util.ArrayList;
import java.util.List;

public class ParenthesisArrangement {
	
	public static void main (String a[]) {
		String [] resultArray = solution(11);
		for (int i = 0; i < resultArray.length; i++) {
			System.out.println(resultArray[i]);
		}
		
	}
	
	public static String[] solution(int n) {
		List<String> stringList = new ArrayList<String>();
		if(n<1 || n> 10) {
			String[] resultArray = stringList.toArray(new String[stringList.size()]);
			return resultArray;
		}
		printArrangement(n, 0, "",stringList);
		String[] resultArray = stringList.toArray(new String[stringList.size()]);
		return resultArray;
	}
	
	 public static void printArrangement(int begining, int end, String s,List<String> stringList) {
	        if (begining == 0 && end == 0) {
	            //System.out.println(s);
	            stringList.add(s);
	        }
	        if (begining > 0) {
	        	printArrangement(begining-1, end+1, s + "(", stringList);
	        }
	        if (end > 0) {
	        	printArrangement(begining, end-1, s + ")", stringList);
	        }
	    }
	   

}
