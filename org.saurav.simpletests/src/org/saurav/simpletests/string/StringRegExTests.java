package org.saurav.simpletests.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Basic string tests which deals with regular expression
 * Just comment and uncomment the specific method required and supply it with the appropriate input
 * @author Saurav Sarkar
 *
 */
public class StringRegExTests {
	public static void main(String a[]){
		String input = "test."; // with .
		String input1 = ".part"; // with empty space
		String input2 = "test"; //without . and empty space.
		String input3ppt = "ppt";
		//checking if "." or empty space is there at the end of the line
		String input3 = "CN=my300120d.s4hana.ondemand.com, OU=SAP Cloud Managed Services, O=SAP SE, L=Walldorf, ST=Baden-Wuerttemberg, C=DE";
		String certPattern = "CN=([^.]+).(s4hana.ondemand.com), OU=.+?, O=.+?, L=.+?, ST=.+?, C=.+?";
		//checkIfStringEndsWith(input1,".part|.partial|.crdownload|download|lnk|.tmp");
		//checkIfStringStartsWith(input3ppt, "ppt");
		checkPatternMatch(input3, certPattern);
	}
	
	//Check if the input string ends with particular set of characters
	public static void checkIfStringEndsWith(String input,String endPattern){
		System.out.println(input.matches(".*("+endPattern+")$")==true);
	}
	//Check if input string starts with particular set of characters
	public static void checkIfStringStartsWith(String input,String startPattern){
		System.out.println(input.matches("^("+startPattern+").*")==true);
	}
	
	public static void checkPatternMatch(String input, String pattern) {
		Pattern certPattern  = Pattern.compile(pattern);
		Matcher matcher = certPattern.matcher(input);
		System.out.println(matcher.matches());
	}
}	
