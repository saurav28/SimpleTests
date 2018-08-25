package org.saurav.simpletests.concurrency;

import java.util.concurrent.Callable;

public class CallableTest {
	
	public void main (String a[]) {
		
		//Thread t1 = new Thread();
		
		
	}
	
	class Worker implements Callable {

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			if(true) {
				return "success";
			}
			return "failure";
		}
		
	}

}
