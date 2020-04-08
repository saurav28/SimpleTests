package org.saurav.simpletests.string;

public class UnicodeString {
	
	public static void main (String a[]) {
		String unicodeString = "saurav";
		
		unicodeString = unicodeString + "(" +"ä" +"_" + ")";
		
		System.out.println(unicodeString);
		
	}

}
