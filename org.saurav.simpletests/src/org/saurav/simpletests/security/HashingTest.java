package org.saurav.simpletests.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Checking the hashing or digital digest
 */
public class HashingTest {

	public static void main(String a[]) {

		HashingTest hashingTest = new HashingTest();

		hashingTest.hashContent("SHA-256", "/Users/i054564/Documents/Work/output.txt");

	}

	private void hashContent(String alogrithm, String filePath) {

		try {
			// creating the object of MessageDigest
			// and getting instance
			// By usng getInstance() method
			MessageDigest sr = MessageDigest.getInstance(alogrithm);
			// creating and intializing
			// object of InputStream
			InputStream is = new FileInputStream(filePath);
			// creating and intializing
			// object of DigestInputStream
			DigestInputStream di = new DigestInputStream(is, sr);
			// getting the message digest
			// using getMessageDigest() method
			MessageDigest str = di.getMessageDigest();
			// display the result
			System.out.println("Status : " + byteToHexString(str.digest()));
		}

		catch (NoSuchAlgorithmException e) {

			System.out.println("Exception thrown : " + e);
		} catch (NullPointerException e) {

			System.out.println("Exception thrown : " + e);
		} catch (FileNotFoundException e) {

			System.out.println("Exception thrown : " + e);
		}
	}

	public String byteToHexString(byte[] input) {
		String output = "";
		for (int i = 0; i < input.length; ++i) {
			output += String.format("%02X", input[i]);
		}
		return output;
	}

}
