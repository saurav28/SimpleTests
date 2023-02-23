package org.saurav.simpletests.io;

import java.io.IOException;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;





public class HttpClientTester {
	
	private byte[] buf=new byte[1024];    //Buffer of Bytes

	
	public static void main(String args[]) {
		
		HttpClientTester httpClient = new HttpClientTester();

		String url = " " ; // Constants.URL;
		//httpClient.executeHttp(url);

		//String url = Constants.ECM_URL;
		//String url = Constants.LOCAL_URL;
		boolean isProtected = false;
		//httpClient.executeHttp(url, Constants.ECM_USER_NAME, Constants.ECM_PASSWORD,isProtected);
		
		httpClient.checkTLSVersion();
		
		

	}
	
	private void checkTLSVersion() {
		SSLContext sslContext;
		
		try {
			sslContext = SSLContext.getInstance("TLSv1");
			sslContext.init(null, null, null);
			SSLParameters defaultSSLParameters = sslContext.getSupportedSSLParameters();
			
			String[] protocols = defaultSSLParameters.getProtocols();
			for (int i = 0; i < protocols.length; i++) {
				System.out.println("protocol name " + protocols[i]);
			}
			
			Provider[] providers = Security.getProviders();
			for (int i = 0; i < providers.length; i++) {
				System.out.println("Provider name "+ providers[i].getName());
			}
			
			URL url = new URL("https://www.google.co.in/");
			HttpsURLConnection urlConnnection =  (HttpsURLConnection) url.openConnection();
			urlConnnection.setRequestProperty("User-Agent", "myuseragent");
			urlConnnection.setSSLSocketFactory(sslContext.getSocketFactory());
			
			int responseCode = urlConnnection.getResponseCode();
			
			System.out.println("Response code " + responseCode);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void executeHttp(String stringUrl,String userName, String password, boolean isProtected) {
		URL url;
		try {
			url = new URL(stringUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			if(isProtected) {
			String encoded = Base64.getEncoder().encodeToString((userName+":"+password).getBytes(StandardCharsets.UTF_8));  //Java 8
			connection.setRequestProperty("Authorization", "Basic "+encoded);
			connection.setRequestProperty("X-CSRF-Token", "fetch");
			}
			int responseCode = connection.getResponseCode();
			InputStream inputStream = connection.getInputStream();
			
			JsonReader jsonReader = Json.createReader(inputStream);
			
//			JSONReader jSonReader = new JSONReader();
//			jSonReader.readJSON(jsonReader);
			
			
			
			System.out.println("the response code:: " + responseCode);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
