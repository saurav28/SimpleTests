package org.saurav.simpletests.concurrency;

public class ThreadLocalTest {
	
	
	
	public static void main (String a[]) {
		
		Thread t1 = new ThreadLocalThread();
		Thread t2 = new ThreadLocalThread();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}

class ThreadLocalThread extends Thread {
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ThreadLocal<Integer> localToThread = new ThreadLocal<Integer>();
		localToThread.set((int) (Math.random() * 100D));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("local thread value" +localToThread.get());
	}
	
}
