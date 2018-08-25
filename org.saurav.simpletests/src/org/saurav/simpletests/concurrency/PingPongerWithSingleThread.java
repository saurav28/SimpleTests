package org.saurav.simpletests.concurrency;

/**
 * Prints the console as "pingpongpingpong".
 * Printing of ping pong has to be done in a different instances of a same thread
 * @author i054564
 *
 */
public class PingPongerWithSingleThread {
	
	boolean pingSignal = true;
	
	public static void main (String a[]) {
		
		PingPongerWithSingleThread pingPonger = new PingPongerWithSingleThread();
		
		pingPonger.doPingPong();
	}
	
	private void doPingPong() {
		PingPong pingThread = new PingPong("ping");
		PingPong pongThread = new PingPong("pong");
		
		pingThread.start();
		pongThread.start();
		
	}
	
	class PingPong extends Thread {
		
		String whatToDo;
		
		public PingPong(String whatToDo) {
			this.whatToDo = whatToDo;
		}
		@Override
		public void run() {
			
				while(true) {
				if("ping".equals(whatToDo)) {
					if(pingSignal) {
					System.out.println(whatToDo);
					pingSignal = false;
					}
				}else {
					if(!pingSignal) {
						System.out.println(whatToDo);
						pingSignal = true;
					}
				}
				
				
				}
		
			}
			
		}


	
	
	

}


