package org.saurav.simpletests.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;

/**
 * Simple reading of stream and some experiments with partial reading
 * 
 * @author i054564
 *
 */
public class TestInputStream {

	public static void main(String a[]) {

		String workingDir = System.getProperty("user.dir");
		File inputFile = new File(workingDir + "/test.html");
		//readFilePartially(inputFile);
		readFileCompletely(inputFile);
		
	}
	/** Read the file completely
	 * 
	 * @param inputFile
	 */
	public static void readFileCompletely(File inputFile) {

		try {
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] buffer = new byte[20];

			while (inputStream.read(buffer) != -1) {

				String str = new String(buffer, "UTF-8");
				System.out.println(str);

			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * Read the file partially
	 * @param inputFile
	 */
	public static void readFilePartially(File inputFile) {

		try {
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] buffer = new byte[20];
			
			//System.out.println("available bytes at the start " + inputStream.available());

			int len = inputStream.read(buffer);

			String str = new String(buffer, "UTF-8");
			System.out.println(str);
			//System.out.println("available bytes" + inputStream.available());
			
			readInSequenceInputStream(buffer, inputStream);
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Concatane the streams. Here we concatane the buffer already read in memory with whatever is left is in the
	 * FileInput Stream
	 * @param buffer
	 * @param fileInputStream
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static  void readInSequenceInputStream(byte[] buffer ,FileInputStream fileInputStream) throws UnsupportedEncodingException, IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
		SequenceInputStream sequenceInputStream = new SequenceInputStream(byteArrayInputStream, fileInputStream);
		while (sequenceInputStream.read(buffer) != -1) {

			String str = new String(buffer, "UTF-8");
			System.out.println(str);

		}
	}
	
	
}
