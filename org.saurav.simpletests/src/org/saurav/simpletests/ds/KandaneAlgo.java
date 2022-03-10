package org.saurav.simpletests.ds;
/**
 * Find the contigous sub array in the array.
 * 
 * Kandane's algorithm can be used in finding brigthest area in a image.
 * https://en.wikipedia.org/wiki/Maximum_subarray_problem
 * 
 * @author saurav.sarkar1@gmail.com
 *
 */
public class KandaneAlgo {

	public static void main (String a[]) {
		
		 int [] intArray = {-2, -3, 4, -1, -2, 1, 5, -3};
	        System.out.println("Maximum contiguous sum is " +
	                                       maxSubArraySum(intArray));
	        
	        
	}

	private static int maxSubArraySum(int[] intArray) {
		int max_so_far = 0; //maximum contigous sum so far
		int max_ending_here = 0;
		
		for (int i = 0; i < intArray.length; i++) {
			max_ending_here = max_ending_here + intArray[i];
			if(max_ending_here <0) {
				max_ending_here =0;
			}if(max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
				
			}
		}
		
		return max_so_far;
	}

}
