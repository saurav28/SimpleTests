package org.saurav.simpletests.exceptions;

public class MasterExceptionHandling {
	
	public static void main (String a[]) {
		MasterExceptionHandling masterExceptionHandling = new MasterExceptionHandling();
		masterExceptionHandling.checkException();
	}
	
	private ExceptionStatusBean checkException() {
		
		boolean ExceptionStatus = ExceptionThrower.checkOnException(true);
		try {
			System.out.println("doing some fun stuff");
		}catch(Exception e) {
			System.out.println("Caught the exception");
		}
		System.out.println("But keep working");
		
		return new ExceptionStatusBean(ExceptionStatus);
	}

}
