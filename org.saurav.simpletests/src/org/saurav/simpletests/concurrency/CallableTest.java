package org.saurav.simpletests.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {
	
	public static void main (String a[]) {
		
		//Thread t1 = new Thread();
		CallableTest callableTest = new CallableTest();
		callableTest.callTheCallable();
		
	}
	
	private void callTheCallable() {
		Callable<String> worker = new Worker();
		try {
			//System.out.println(worker.call());
			
			
			//lambda expression way of calling of callable worker
			
			Callable<String> callable  = ()-> {
				if(true) {
					return "success";
				}
				return "failure";
			};
			
			
			//call directly
			
			// System.out.println(callable.call());
			
			
			//Initiate Executor Service and submit the callable
			
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			
			Future<String> future = executorService.submit(callable);
			
			String result = future.get();
			
			System.out.println("callable result " + result);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// plain vanialla calling of callable worker
	class Worker implements Callable<String> {

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
