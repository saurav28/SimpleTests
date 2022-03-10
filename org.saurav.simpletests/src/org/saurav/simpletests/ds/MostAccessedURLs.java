package org.saurav.simpletests.ds;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MostAccessedURLs {
	
	public static void main (String a[]) {
		String[] resultArray = solution(3,new String[] {"10 200 sad", "10 100 sad","10 400 sad","10 200 mad","10 200 dad"});
		
		for (int i = 0; i < resultArray.length; i++) {
			System.out.println(resultArray[i]);
		}
		
	}
	
	public static String[] solution(int n, String[] logs) {
		
       
		List<String> resultList = new ArrayList<String>();
		List<String> asList = new ArrayList<String>();
		if(n <1 || n >30) {
			return resultList.toArray(new String[resultList.size()]);
		}
		for (int i = 0; i < logs.length; i++) {
			String statusCode = logs[i].split(" ")[1];
			int parseInt = Integer.parseInt(statusCode);
			if(parseInt == 200) {
				asList.add(logs[i].split(" ")[2]);
			}
		}
		
		Map<String, Long> frequencyMap = (Map<String, Long>) asList.stream()
		        .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
		
		
		
		for (int i = 0; i < n; i++) {
			Iterator<Entry<String, Long>> iterator1 = frequencyMap.entrySet().iterator();
			Long oldValue = (long) 0;
			while(iterator1.hasNext()) {
				Map.Entry pair = (Map.Entry)iterator1.next();
				Long longValue = (Long) pair.getValue();
				if(oldValue == longValue || oldValue == 0) {
					oldValue = longValue;
					resultList.add((String) pair.getKey());
				}else {
					break;
				}
			}
			
			
		}
		

		List<Map.Entry<String, Long>> result = frequencyMap.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .limit(n)
		        .collect(Collectors.toList());
			
		
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Entry<String, Long> entry = (Entry<String, Long>) iterator.next();
			resultList.add(entry.getKey());
			
		}
		
		return resultList.toArray(new String[resultList.size()]);
    }

}
