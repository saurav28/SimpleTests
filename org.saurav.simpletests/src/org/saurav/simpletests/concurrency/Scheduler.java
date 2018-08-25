package org.saurav.simpletests.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class to test the java scheduler implementations
 * Timer task code is inspired from  https://www.mkyong.com/java/how-to-run-a-task-periodically-in-java/
 * @author i054564
 *
 */
public class Scheduler {
	
	public static void main (String a[]) {
		
		//scheduleUsingTimer();	
		scheduleUsingExecutorService();
		
	}
	
	/**
	 * Schedule job using java executor service
	 */
	public static void scheduleUsingExecutorService() {
		//Executor
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("Current time:: " + System.currentTimeMillis());
				
			}
			
		}, 0, 1000, TimeUnit.MILLISECONDS);
		
		//executor.shutdown();
	}
	/**
	 * Schedule job using java.util.timer
	 */
	public static void scheduleUsingTimer() {
		Scheduler sd = new Scheduler();
		Timer timer = new Timer();
		ScheduledTask st = sd.new ScheduledTask();
		timer.schedule(st, 0, 1000);
	}
	
	
	class ScheduledTask extends TimerTask {
		
		public ScheduledTask() {
			// TODO Auto-generated constructor stub
		}
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Current time:: " + System.currentTimeMillis());
			
		}
		
	}

}
