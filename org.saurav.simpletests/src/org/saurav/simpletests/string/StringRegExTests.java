package org.saurav.simpletests.string;
/**
 * Basic string tests which deals with regular expression
 * Just comment and uncomment the specific method required and supply it with the appropriate input
 * @author Saurav Sarkar
 *
 */
public class StringRegExTests {
	public static void main(String a[]){
		String input = "test."; // with .
		String input1 = "test1 "; // with empty space
		String input2 = "test"; //without . and empty space.
		//checking if "." or empty space is there at the end of the line
		checkIfStringEndsWith(input1,"\\.|\\s");
		
	}
	
	//Check if the input string ends with particular set of characters
	public static void checkIfStringEndsWith(String input,String endPattern){
		System.out.println(input.matches(".*("+endPattern+")$")==true);
	}
}	
