package org.saurav.simpletests.exceptions;

public class ExceptionThrower {
	
	public static boolean checkOnException(boolean shouldThrowException) {
		
		if(shouldThrowException) {
		checkIfException(shouldThrowException);
		return true;
		}
		
		return false;
		
	}

	private static void checkIfException(boolean shouldThrowException) {

		if (shouldThrowException) {
			throw new RuntimeException("Throw connection");

		}
}
	
}
