package org.saurav.simpletests.string;

import java.io.File;

public class StringManipulator {
	
	public static void main(String a[]){
		String testString ="/UserId/DCode/deep";
		
		testString = testString.replace("/", File.separator);
		//testString = testString.replace("/", "_");
		String test1String ="004\\034556";
		testSplitStrings(testString);
	}

	/**
	 * Test the split string
	 * @param path
	 */
	public static void testSplitStrings(String path){
		System.out.println("splitting of sprint starts \n");
		String[] paths = path.split("\\\\");
		for (int i = 0; i < paths.length; i++) {
			System.out.println("paths::"+i+" "+paths[i]+"\n");
		}
		System.out.println("splitting of sprint ends");
	}
}
