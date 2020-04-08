package org.saurav.simpletests.io;

import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;

import org.saurav.simpletests.Constants;



public class HttpClientTester {
	
	private byte[] buf=new byte[1024];    //Buffer of Bytes

	
	public static void main(String args[]) {
		
		HttpClientTester httpClient = new HttpClientTester();
		String url = Constants.URL;
		//httpClient.executeHttp(url);
	}
	
	public void executeHttp(String stringUrl,String userName, String password) {
		URL url;
		try {
			url = new URL(stringUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			String encoded = Base64.getEncoder().encodeToString((userName+":"+password).getBytes(StandardCharsets.UTF_8));  //Java 8
			connection.setRequestProperty("Authorization", "Basic "+encoded);
			connection.setRequestProperty("X-CSRF-Token", "fetch");
			
			int responseCode = connection.getResponseCode();
			InputStream inputStream = connection.getInputStream();
			
			JsonReader jsonReader = Json.createReader(inputStream);
			
			JSONReader jSonReader = new JSONReader();
			jSonReader.readJSON(jsonReader);
			
			
			
			System.out.println("the response code" + responseCode);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
