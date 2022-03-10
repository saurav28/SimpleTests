package org.saurav.simpletests.ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *  Given the daily prices of a stock , find the maximum profit which can be made
 *  by buying and selling the stock only once.
 * @author saurav.sarkar1@gmail.com
 *
 */
public class MaximizeProfit {
	
	
	
	public static void main(String a[]) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] values;
		try {
			values = br.readLine().split(" ");
		
		int[] valuesarray = new int[values.length];
		
		
		for (int i = 0; i < values.length; i++) {
			valuesarray[i] = Integer.parseInt(values[i]);
		}
		
		MaximizeProfit maxProfit = new MaximizeProfit();
		maxProfit.makeProfit(valuesarray);
		}catch (Exception e) {
			System.out.println(" Exception occurred " + e);
		}
		
	}
	
	private void makeProfit(int input[]) {
		
		int maxBuy = input[0]; int maxSell =input[0];
		int maxProfit =0;
		
		for (int i : input) {
			
			if(i < maxBuy) {
				maxBuy = i;
				maxSell =i;
			}
			if(i > maxSell) {
				maxSell = i;
			}
			int profit = maxSell - maxBuy;
			
			maxProfit = Math.max(maxProfit, profit);
		}
		
		System.out.println(" Profit which can be achieved is " + maxProfit);
	}

}
