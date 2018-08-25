package org.saurav.simpletests.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * Prints the console as "pingpongpingpong". Printing of ping pong has to be
 * done in a different threads.
 * 
 * @author i054564
 *
 */
public class PingPongerWithDifferentThreads {

	ReadLock lock = new ReentrantReadWriteLock().readLock();
	boolean pingSignal = true;

	public static void main(String a[]) {

		PingPongerWithDifferentThreads pingPonger = new PingPongerWithDifferentThreads();

		pingPonger.doPingPong();
	}

	private void doPingPong() {
		Thread pingThread = new Thread(new Ping(this));
		Thread pongThread = new Thread(new Pong(this));

		pingThread.start();
		pongThread.start();

	}

	class Ping extends Thread {
		Object lockObject;
		
		public Ping(Object lockObject){
			this.lockObject = lockObject;
		}

		@Override
		public void run() {
			
			synchronized(lockObject) {
			for(int i =0;i<10;i++) {
			System.out.println("ping");
			lockObject.notify(); // notfies the other threads waiting on this object
			try {
				lockObject.wait(); // 
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			}
			lockObject.notify();

		}
		}
	}
	

	class Pong extends Thread {
		
		Object lockObject;
		
		public Pong(Object lockObject) {
			this.lockObject = lockObject;
		}

		@Override
		public void run() {
			
			synchronized (lockObject) {
				for(int i =0;i<10;i++) {
					System.out.println("pong");
					lockObject.notify();
					try {
						lockObject.wait();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
			}
				lockObject.notify();
			

		}
	}
	}

}
	

	
