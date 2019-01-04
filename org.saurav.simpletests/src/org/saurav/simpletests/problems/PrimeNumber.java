package org.saurav.simpletests.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
/**
 * Hacker earth practice problem
 * 
 * 
 * You are given an integer N. You need to print the series of all prime numbers till N.

Input Format

The first and only line of the input contains a single integer N denoting the number till where you need to find the series of prime number.

Output Format

Print the desired output in single line separated by spaces.

Constraints

1<=N<=1000
 * 
 * @author Saurav Sarkar
 *
 */
public class PrimeNumber {
	
	public static void main (String a[]) {
		
		PrimeNumber primeNumber = new PrimeNumber();
		Scan scan = primeNumber.new Scan();
		try {
			int num = scan.scanInt();
			primeNumber.validateInput(num);
			primeNumber.printPrime(Double.valueOf(num));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void printPrime(Double num) {
		for (double i=0; i<= num ; i++) {
			if(checkPrime(i)) {
				System.out.print(Double.valueOf(i).intValue()+ " ");
			}
		}
	}
	
	
	private void validateInput(int input) {
		if (input < 1 || input > 1000) {
			System.exit(0);
		}
	}
	
	private boolean checkPrime(Double num) {
		boolean result = true;
		Double sqroot = Math.sqrt(num);
		
		if(num <= 1 || num != 2 && num %2 ==0) {
			result = false;
		}else {
			for (int i = 3; i<= sqroot; i+= 2) {
				if(num % i == 0) {
					result =false;
					break;
				}
			}
		}
		
		return result;
	}
	
	
	class Scan {
		private byte[] buf = new byte[1024]; // Buffer of Bytes
		private int index;
		private InputStream in;
		private int total;

		public Scan() {
			in = System.in;
		}

		public int scan() throws IOException // Scan method used to scan buf
		{
			if (total < 0)
				throw new InputMismatchException();
			if (index >= total) {
				index = 0;
				total = in.read(buf);
				if (total <= 0)
					return -1;
			}
			return buf[index++];
		}

		public int scanInt() throws IOException {
			int integer = 0;
			int n = scan();
			while (isWhiteSpace(n)) // Removing starting whitespaces
				n = scan();
			int neg = 1;
			if (n == '-') // If Negative Sign encounters
			{
				neg = -1;
				n = scan();
			}
			while (!isWhiteSpace(n)) {
				if (n >= '0' && n <= '9') {
					integer *= 10;
					integer += n - '0';
					n = scan();
				} else
					throw new InputMismatchException();
			}
			return neg * integer;
		}

		private boolean isWhiteSpace(int n) {
			if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
				return true;
			return false;
		}
	}

	class Print {
		private final BufferedWriter bw;

		public Print() {
			this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public void print(Object object) throws IOException {
			bw.append("" + object);

		}

		public void println(Object object) throws IOException {
			print(object);
			bw.append("\n");
		}

		public void close() throws IOException {
			bw.close();
		}
	}

}
