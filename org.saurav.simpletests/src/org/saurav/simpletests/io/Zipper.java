package org.saurav.simpletests.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class to zip the files
 * 
 * Code is inspired from https://www.mkyong.com/java/how-to-compress-files-in-zip-format/
 * 
 * @author Saurav Sarkar
 *
 */
public class Zipper {

	
	public static void main( String[] args )
    {
    	byte[] buffer = new byte[1024];
    	
    	try{
    		
    		FileOutputStream fos = new FileOutputStream("/Users/xxxx/Documents/Work//Data/Zipper/test.zip");
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		ZipEntry ze= new ZipEntry("Sonar_Gerrit_Issue.png");
    		
    		zos.putNextEntry(ze);
    		FileInputStream in = new FileInputStream("/Users/xxxx/Documents/Work/Data/Zipper/Sonar_Gerrit_Issue.png");
    		File file  = new File("/Users/i054564/Documents/Work/Data/Zipper/Sonar_Gerrit_Issue.png");
    		
    		int len;;
    		while ((len = in.read(buffer)) > 0) {
    			zos.write(buffer, 0, len);
    		}

    		in.close();
    		zos.closeEntry();
           
    		//remember close it
    		zos.close();
          
    		System.out.println("Done");

    	}catch(IOException ex){
    	   ex.printStackTrace();
    	}
    }
}
