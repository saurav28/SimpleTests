package org.saurav.simpletests.string;

public class SDCPublicURLGenerator {

	public static void main(String args[]) {

		if (args.length ==0) {
			System.out.println("Please enter a valid url"); //no validation of url but just checking if URL is passed
			return;
		}

		String url = args[0];

		System.out.println("Original URL" + url);

		String convertedURL = url.replaceAll("v1/open", "dl");

		System.out.println("Converted URL" + convertedURL);

	}

}
