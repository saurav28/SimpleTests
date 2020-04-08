package org.saurav.simpletests.concurrency;


import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTestTreeSet {
    private Set<String> set;
    private Object object = new Object();
    private int THREADS =  100;
    private long averageTime = 0;

public static void main(String args[]) {
	ThreadTestTreeSet threadTestTreeSet = new ThreadTestTreeSet();
	threadTestTreeSet.testThreadTree();

}
	
private void testThreadTree() {
    for (int i = 0; i < 1; i++) {
        set = new TreeSet<>();
//            map = new ConcurrentSkipListMap<>(Ordering.natural());

        long time = System.nanoTime();
        ExecutorService service = Executors.newFixedThreadPool(THREADS);

        for (int j = 0; j < 1000; j++) {
            //final int finalJ = j;
            service.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(THREADS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   
					try{
                    long threadId = Thread.currentThread().getId();
                   // synchronized (object) {
                    	set.add("tag"+threadId);
					//}
                    	
					
                    
					}catch(Exception e){
						System.out.println("Exception in :: " + String.valueOf(Thread.currentThread().getId()));
						System.out.println("Set-> "+set);
						e.printStackTrace();
					}
            }});
        }
        service.shutdown();
        try {
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long timeUsed = (System.nanoTime() - time) / 1000000L;
        averageTime += timeUsed;
        System.out.println("All threads are completed in "
                + timeUsed + " ms");
        System.out.println(set);
    }
    System.out.println("The average time is " + averageTime / 10 + " ms");
}

}