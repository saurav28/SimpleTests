package org.saurav.simpletests.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class DivideNumber {
	
	public static void main (String a[]) {
		DivideNumber divideNumber = new DivideNumber();
		Scan scan = divideNumber.new Scan();
		try {
			
			int count = scan.scanInt();
			if(!divideNumber.validateInput(count)) {
				return;
			}
			for (int i =0 ;i < count; i++) {
			int num = scan.scanInt();
			if (!divideNumber.validateInput(num)) {
					System.out.println(-1);
					continue;
			}
			Integer[] factors = divideNumber.findFactors(num);
			long output = divideNumber.findSumAndMaximizeHashing(factors, num);
			//int output = divideNumber.getFourNumbersSum(factors, num);
			System.out.println(output);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private boolean validateInput(int num) {
		boolean result =true;
		if (num < 1 || num > 4*10000) {
			result  =false;
		}
		return result;
		
	}
	
	private Integer[] findFactors (int num) {
		
		ArrayList<Integer> factorsList = new ArrayList<Integer>();
		
		for (int i =1; i <= Math.sqrt(num) ;i++) {
			if (num % i == 0) {
				factorsList.add(i);
				if (i != num / i) {
					factorsList.add(num / i);
				}
			}
		}
		
		return factorsList.toArray(new Integer[factorsList.size()]);
		
	}
	
	private int findSumAndMaximize(Integer[] factors,int num) {
		int output = 0;
		int size = factors.length;
		
		for(int i =0 ; i < size ; i++) {
			for (int j = 0 ; j <size ; j++) {
				for (int k =0 ; k <size; k ++) {
					for (int l = 0; l<size; l ++) {
						int sum = factors[i]+factors[j]+factors[k]+factors[l];
						if(sum == num) {
							int product = factors[i]*factors[j]*factors[k]*factors[l];
							if(output < product) {
								output = product;
							}
						}
					}
				}
			}
		}
		
		if(output == 0 ) {
			output = -1;
		}
		return output;
	}
	
	private long findSumAndMaximizeHashing(Integer[] factors,int num) {
		
		long output = -1;
		
		int n = factors.length;
		HashMap<Integer, Point> map = new HashMap<Integer,Point>();
		
		for (int i = 0; i<= n-1 ; i ++) {
			for (int j =i ; j< n; j++) {
				int sum = factors [i] + factors[j];
				if(map.containsKey(sum)) {
					Point point = map.get(sum);
					long oldMul = point.x*point.y;
					long curMul = factors[i]*factors[j];
					if(curMul >oldMul) {
						map.put(sum, new Point(factors[i],factors[j]));
					}
				}else {
				map.put(sum, new Point(factors[i],factors[j]));
				}
				
				int diff = num - sum;
				if(map.containsKey(diff)) {
					Point p = map.get(diff);
					long mul = (long)factors[i]* (long)factors[j]*(long)p.x*(long)p.y;
					if(mul >output) {
						output =mul;
					}
				}
			}
			
			
		}
		
		
		
//		for (int i=0; i< n; i++) {
//			for (int j = 0 ; j<n;j++) {
//				int number = num - (factors[i]+factors[j]);
//				if(map.containsKey(number)) {
//					Point p = map.get(number);
//					int product = factors[i]*factors[j]*factors[p.x]*factors[p.y];
//					if(output < product) {
//						output = product;
//					}
//				}
//			}
//		}
		
		return output;
	}
	
	
	class Point {
		public int x;
		public int y;
		
		Point(int x, int y){
			this.x = x;
			this.y= y;
		}
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
