package org.saurav.simpletests.ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SortingTest {
	
	public static void main(String a[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] values = br.readLine().split(" ");
		Integer[] valuesarray = new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			valuesarray[i] = Integer.parseInt(values[i]);
		}
		InsertionSort sort = new InsertionSort();
		sort.sort(valuesarray);
	}

}

class InsertionSort {
	public void sort(Integer[] objArray){
		for (int i = 1; i < objArray.length; i++) {
			Integer value = objArray[i];
			for(int j=i-1;j>=0;j--){
				if(objArray[j] > value) {
					int temp = objArray[j];
					objArray[j] = value;
					objArray[j+1] = temp;
					
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
	public void sort(Object[] objArray){
		
	}
}
class QuickSort {
	public void sort(Object[] objArray){
		
	}
}
class MergeSort {
	public void sort(Object[] objArray){
		
	}
}