package org.saurav.simpletests.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

/**
 * Problem for August circuit
 * 
 * 
 * You have  dollars with you. You want to put it into a Bank, namely Picu Bank. 
 * This bank has a peculiar behavior for interest. Regardless of the Bank deposit amount, every month it adds  dollars to your bank account and this continues till  months. 
 * Exaxtly on  months, it adds  dollars  to your bank account. This scanario repeats again in same manner.( i.e on the  month  dollars are added, and so on.. ). 
 * Your task is to find out how many months does it take for the dollar amount to reach at least , in the bank account .    

Input:

Input starts with an integer , denoting the number of test cases.  Each case starts with 5 integers  , and as described in problem statement.
 * 
 * @author 
 *
 */
public class PicuBank {

	int D, A, M, B, X;
	static int[] countArray;
	// static BufferedReader br = new BufferedReader(new
	// InputStreamReader(System.in));
	static Scan scan;

	public static void main(String a[]) {
		
		PicuBank picuBank = new PicuBank();
		scan = picuBank.new Scan();
		int count = 0;
		try {
			count = scan.scanInt();
			if (count < 1 || count > 100000) {
				System.exit(0);
			}
			countArray = new int[count];
			Print print = picuBank.new Print();
			for (int i = 0; i < count; i++) {
				picuBank.inputData();
				int months = picuBank.caculcateMonths();
				print.println(months);
				
			}
			print.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void inputData() {

		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			// String line = br.readLine();
			// String[] values= line.trim().split("\\s+");

			D = scan.scanInt();
			validateInput(D);
			A = scan.scanInt();
			validateInput(A);
			M = scan.scanInt();
			validateInput(M);
			B = scan.scanInt();
			validateInput(B);
			if (B > A) {
				System.exit(0);
			}
			X = scan.scanInt();
			validateInput(X);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void validateInput(int input) {
		if (input < 1 || input > 1000000000) {
			System.exit(0);
		}
	}

	private int caculcateMonths() {
		int months = 0;
		int sum = D;

		if (sum >= X) {
			return months;
		}
		
		
		sum = X- D;
		
		int factor = sum/(M*A +B);
	
		int rem = sum%(M*A +B); 
		
		months = factor*(M+1);
		
		if(rem %A == 0) {
			months = months + rem/A;
		}else {
			
			months = months + rem/A+1;
		}
		
		return months;

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
