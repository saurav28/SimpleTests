package org.saurav.simpletests.ds;

import java.io.BufferedReader;

import java.io.InputStreamReader;


/**
 *  A test program for different type of sorting algorithms.

 *  For now insertion, selection and quick sort are supported.
 *  Merge , bubble and radix sort are planned for future.

 *  
 *  The quick sort program has been taken from http://java2novice.com/java-sorting-algorithms/quick-sort/
 *  
 *  Merge Sort taken from https://www.geeksforgeeks.org/merge-sort/
 *  
 *  Counting Sort from  https://www.programiz.com/dsa/counting-sort
 *  
 *  Just uncomment the specific sorting algorithm you want to run
 * @author Saurav Sarkar
 *
 */
public class SortingTest {
	
	public static void main(String a[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] values = br.readLine().split(" ");
		int[] valuesarray = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			valuesarray[i] = Integer.parseInt(values[i]);
		}

//		InsertionSort sort = new InsertionSort();
//		sort.sort(valuesarray);
		
//		SelectionSort sort = new SelectionSort();
//		sort.sort(valuesarray);
		
//		QuickSort sort = new QuickSort();
//		sort.sort(valuesarray);
		
		System.out.println("Starting to sort");
//		MergeSort sort = new MergeSort();
//		sort.sort(valuesarray, 0,valuesarray.length-1);
//		System.out.println("\nSorted array");
//		sort.printNumbers(valuesarray);
		
//		
//		BubbleSort sort = new BubbleSort();
//		sort.sort(valuesarray);
//		System.out.println("\nSorted array");
//		sort.printNumbers(valuesarray);
		
		// CountingSort sort = new CountingSort();
		// sort.sort(valuesarray);

		if(a[0].isEmpty()){
			System.out.println("Please provide an input for the type of sort you want to run");
		}
		if(a[0].equals("insertion")) {
					InsertionSort sort = new InsertionSort();
					sort.sort(valuesarray);
		}else if(a[0].equals("selection")) {
					SelectionSort sort = new SelectionSort();
					sort.sort(valuesarray);
		}
		else if(a[0].equals("quick")) {
			QuickSort sort = new QuickSort();
			sort.sort(valuesarray);
		}else {
			System.out.println("Invalid input");
		}

	}

}

class BubbleSort {
	
	public void sort(int[] objArray) {
		
		int length = objArray.length;
		
		for(int i = 0; i< length ; i++ ) {
			for (int j= i+1; j< length ;j++) {
				if(objArray[i] > objArray[j]) {
					exchangeNumbers(objArray, i, j);
				}
			}
		}
	}
	
	 private void exchangeNumbers(int[] objArray ,int i, int j) {
	        int temp = objArray[i];
	        objArray[i] = objArray[j];
	        objArray[j] = temp;
	    }
	 
	 void printNumbers(int[] objArray) {
			for (int i = 0; i < objArray.length; i++) {
				System.out.print(objArray[i]+",");
			}
			System.out.println("\n");
		}
}

class InsertionSort {
	public void sort(int[] objArray){
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

	private void printNumbers(int[] objArray) {
		for (int i = 0; i < objArray.length; i++) {
			System.out.print(objArray[i]+",");
		}
		System.out.println("\n");
	}
}

class SelectionSort {
	public void sort(int[] objArray){
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
	private void printNumbers(int[] objArray) {
		for (int i = 0; i < objArray.length; i++) {
			System.out.print(objArray[i]+",");
		}
		System.out.println("\n");
	}
}
class QuickSort {
	private int array[];
    private int length; 
	
	 public void sort(int[] inputArr) {
         
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
	             * is greater than the pivot value, and also we will identify a number
	             * from right side which is less than the pivot value. Once the search
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
	    private void printNumbers(int[] objArray) {
			for (int i = 0; i < objArray.length; i++) {
				System.out.print(objArray[i]+",");
			}
			System.out.println("\n");
		}
	
}
class MergeSort {
	 void merge(int arr[], int l, int m, int r)
	    {
	        // Find sizes of two subarrays to be merged
	        int n1 = m - l + 1;
	        int n2 = r - m;
	 
	        /* Create temp arrays */
	        int L[] = new int[n1];
	        int R[] = new int[n2];
	 
	        /*Copy data to temp arrays*/
	        for (int i = 0; i < n1; ++i)
	            L[i] = arr[l + i];
	        for (int j = 0; j < n2; ++j)
	            R[j] = arr[m + 1 + j];
	 
	        /* Merge the temp arrays */
	 
	        // Initial indexes of first and second subarrays
	        int i = 0, j = 0;
	 
	        // Initial index of merged subarry array
	        int k = l;
	        while (i < n1 && j < n2) {
	            if (L[i] <= R[j]) {
	                arr[k] = L[i];
	                i++;
	            }
	            else {
	                arr[k] = R[j];
	                j++;
	            }
	            k++;
	        }
	 
	        /* Copy remaining elements of L[] if any */
	        while (i < n1) {
	            arr[k] = L[i];
	            i++;
	            k++;
	        }
	 
	        /* Copy remaining elements of R[] if any */
	        while (j < n2) {
	            arr[k] = R[j];
	            j++;
	            k++;
	        }
	    }
	 
	    // Main function that sorts arr[l..r] using
	    // merge()
	    void sort(int arr[], int l, int r)
	    {
	        if (l < r) {
	            // Find the middle point
	            int m =l+ (r-l)/2; // need to find the exact middle point of the bigger array
	 
	            // Sort first and second halves
	            sort(arr, l, m);
	            sort(arr, m + 1, r);
	 
	            // Merge the sorted halves
	            merge(arr, l, m, r);
	        }
	    }

	  
	   void printNumbers(int[] objArray) {
			for (int i = 0; i < objArray.length; i++) {
				System.out.print(objArray[i]+",");
			}
			System.out.println("\n");
		}
	   
}
	   
class CountingSort {
	
	void sort(int arr[]) {
		
		int length = arr.length;
		int max = 0;
		
		//finding the max element
		
		for (int i = 0; i < length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		
		//initialize the count array with the max element as the range
		
		int count[] = new int[max + 1];
		
		//store the count of each element
		
		for(int i=0 ; i<length ; i++) {
			count[arr[i]] = count[arr[i]] + 1;
		}
		
		//store the cumulative count
		
		for(int i= 1; i <= max; i++) {
			count[i] = count[i-1] + count[i];
		}
		
		int[] output = new int[length];
		
		for(int i = length -1 ; i >=0 ; i--) {
			output[count[arr[i]] -1] = arr[i];
			count[arr[i]]--;
		}
		
		for(int i = 0; i < length ; i++) {
			arr[i] = output[i];
 		}
		
		printNumbers(arr);
	}
	
	
	 private void printNumbers(int[] objArray) {
			for (int i = 0; i < objArray.length; i++) {
				System.out.print(objArray[i]+",");
			}
			System.out.println("\n");
		}
	
}
