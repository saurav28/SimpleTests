package org.saurav.simpletests.problems;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTests {
	
	public static void main(String a[]){
		try {
		Path mobileDocsRoot = Paths.get("C:\\Users\\i054564\\Docs");
		
		Path childPath = Paths.get("C:\\Users\\i054564\\Docs\\Documents\\Test12\\Test");
		
		Path relativize = mobileDocsRoot.relativize(childPath);
		System.out.println("the relative path::" +relativize);
		
		Path subpath = relativize.subpath(0, 2);
		System.out.println("the sub path ::" +subpath);
		
		
			System.out.println("the absoulte path of sub path :: " +mobileDocsRoot.resolve(subpath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
