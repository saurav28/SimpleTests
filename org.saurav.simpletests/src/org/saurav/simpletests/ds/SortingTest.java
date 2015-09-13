package org.saurav.simpletests.ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *  A test program for different type of sorting algorithms.
 *  Currently supported are insertion, selection and quick sort
 *  Merge , bubble and radix sort are planned for future.
 *  
 *  The quick sort program has been taken from http://java2novice.com/java-sorting-algorithms/quick-sort/
 *  
 *  Just uncomment the specific sorting algorithm you want to run
 * @author Saurav Sarkar
 *
 */
public class SortingTest {
	
	public static void main(String a[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] values = br.readLine().split(" ");
		Integer[] valuesarray = new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			valuesarray[i] = Integer.parseInt(values[i]);
		}
//		InsertionSort sort = new InsertionSort();
//		sort.sort(valuesarray);
		
//		SelectionSort sort = new SelectionSort();
//		sort.sort(valuesarray);
		
		QuickSort sort = new QuickSort();
		sort.sort(valuesarray);
	}

}

class InsertionSort {
	public void sort(Integer[] objArray){
		for (int i = 1; i < objArray.length; i++) {
			Integer value = objArray[i];
			for(int j=i-1;j>=0;j--){
				if(objArray[j] > value) {
					//int temp = objArray[j];
					//objArray[j] = value;
					objArray[j+1] = objArray[j];
					
				}
			}
		printNumbers(objArray);
		}
		
	}

	private void printNumbers(Integer[] objArray) {
		for (int i = 0; i < objArray.length; i++) {
			System.out.print(objArray[i]+",");
		}
		System.out.println("\n");
	}
}

class SelectionSort {
	public void sort(Integer[] objArray){
		for (int i = 0; i < objArray.length-1; i++) {
			int min = i;
			for (int j = i+1; j < objArray.length; j++) {
				if(objArray[min]  > objArray[j]){
					min = j;
				}
			
			}
			if(i !=min) {
			int temp = objArray[i];
			objArray[i] = objArray[min];
			objArray[min] = temp;
			printNumbers(objArray);
			}
		}
		
	}
	private void printNumbers(Integer[] objArray) {
		for (int i = 0; i < objArray.length; i++) {
			System.out.print(objArray[i]+",");
		}
		System.out.println("\n");
	}
}
class QuickSort {
	private Integer array[];
    private int length; 
	
	 public void sort(Integer[] inputArr) {
         
	        if (inputArr == null || inputArr.length == 0) {
	            return;
	        }
	        this.array = inputArr;
	        length = inputArr.length;
	        quickSort(0, length - 1);
	    }
	 
	    private void quickSort(int lowerIndex, int higherIndex) {
	         
	        int i = lowerIndex;
	        int j = higherIndex;
	        // calculate pivot number, I am taking pivot as middle index number
	        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
	        // Divide into two arrays
	        while (i <= j) {
	            /**
	             * In each iteration, we will identify a number from left side which
	             * is greater then the pivot value, and also we will identify a number
	             * from right side which is less then the pivot value. Once the search
	             * is done, then we exchange both numbers.
	             */
	            while (array[i] < pivot) {
	                i++;
	            }
	            while (array[j] > pivot) {
	                j--;
	            }
	            if (i <= j) {
	                exchangeNumbers(i, j);
	                //move index to next position on both sides
	                i++;
	                j--;
	            }
	        }
	        // call quickSort() method recursively
	        printNumbers(array);
	        if (lowerIndex < j)
	        	//System.out.println("new higher limit for lower array "+j);
	            quickSort(lowerIndex, j);
	        if (i < higherIndex)
	        	//System.out.println("new lower limit for higher array "+i);
	            quickSort(i, higherIndex);
	    }
	 
	    private void exchangeNumbers(int i, int j) {
	        int temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	    }
	    private void printNumbers(Integer[] objArray) {
			for (int i = 0; i < objArray.length; i++) {
				System.out.print(objArray[i]+",");
			}
			System.out.println("\n");
		}
	
}
class MergeSort {
	public void sort(Object[] objArray){
		
	}
}