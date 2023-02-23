package org.saurav.simpletests.io;

public class RedisConnector {
	
	public static void main (String a[]) {
		RedisConnector.connectToRedis();
	}
	
	private static void connectToRedis() {
		
//		RedisClient client = RedisClient.create("redis://localhost");
//		StatefulRedisConnection<String, String> connection = client.connect();
//		System.out.println(connection.isOpen());
//		RedisCommands<String, String> sync = connection.sync();
//		
//		
//		//hashes
//		sync.hset("recordname", "Firstname", "John");
//		sync.hset("recordname", "lastname", "Smith");
//		
//		System.out.println(sync.hgetall("recordname"));
//		
//		//lists
//		
//		sync.lpush("alphabetList","a");
//		sync.lpush("alphabetlist", "b");
//		
//		System.out.println(sync.rpop("alphabetlist"));
		
	}

}
