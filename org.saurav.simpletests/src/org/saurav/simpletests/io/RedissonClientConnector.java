package org.saurav.simpletests.io;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonClientConnector {
	
	public static void main (String a[]) {
		
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		
		//create Redissoninstance
		
		RedissonClient redisson = Redisson.create(config);
		RMap<Object, Object> myMap = redisson.getMap("myMap");
		myMap.put("test", "string");
		System.out.println(myMap);
		RBucket<Object> bucket = redisson.getBucket("bucket1");
		bucket.set("value in the bucket");
		
		System.out.println("Bucket value ::" + bucket.get());
		
		
		
	}
	
	
}
