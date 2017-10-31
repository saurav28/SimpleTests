package org.saurav.simpletests.reactive;

import static org.junit.Assert.*;

import org.junit.Test;

import io.reactivex.Observable;

public class RxJavaUnitTest {
	
	String result="";

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	 @Test
	    public void returnAValue(){
	        result = "";
	        Observable<String> observer = Observable.just("Hello"); // provides datea
	        observer.subscribe(s -> result=s); // Callable as subscriber
	        assertTrue(result.equals("Hello"));
	    }

}
