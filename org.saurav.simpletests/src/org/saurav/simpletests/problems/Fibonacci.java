package org.saurav.simpletests.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci {
	
	public static void main (String a[]){
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int fibnacciTerm = Integer.parseInt(br.readLine());
		for(int i=0;i<fibnacciTerm;i++){
			System.out.println(printFibonacciRecursively(i));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int printFibonacciRecursively(int fibonaaciTerm){
		if(fibonaaciTerm == 0){
			return 0;
		}
		else if(fibonaaciTerm == 1){
			return 1;
		}
		else{
			return printFibonacciRecursively(fibonaaciTerm-1) + printFibonacciRecursively(fibonaaciTerm-2);
		}
	}
}
