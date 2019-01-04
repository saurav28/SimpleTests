package org.saurav.simpletests.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * 
 * @author Saurav Sarkar
 *
 */
public class CityTravel {
	
	int S, X ,N;
	HashMap <Integer,Integer> deviations = new HashMap<Integer, Integer> ();
	
	public static void main (String a[]) {
		CityTravel cityTravel = new CityTravel();
		Scan scan = cityTravel.new Scan();
		try {
			cityTravel.inputData(scan);
			System.out.println(cityTravel.computeTravel());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private void inputData(Scan scan) throws IOException {
		
		S = scan.scanInt();
		if(S < 1) {
			System.exit(0);
		}
		X = scan.scanInt();
		validateInput(X);
		N = scan.scanInt();
		if( N < 0 || N > 1000) {
			System.exit(0);
		}
		
		for (int i = 0; i< N ;i ++) {
			int T = scan.scanInt();
			if( T  < 1) {
				System.exit(0);
			}
			int Y = scan.scanInt();
			validateInput(Y);
			
			if(deviations.putIfAbsent(T, Y) !=null) {
				System.exit(0);
			}
			
		}
		
	}
	
	private void validateInput(int input) {
		if (input > 1000000000) {
			System.exit(0);
		}
	}
	
	private int computeTravel () {
		int sum = 0;
		int daysCounter = 0;
		while (sum < S) {
			daysCounter = daysCounter + 1;
			Integer deviation = deviations.get(daysCounter);
			if(deviation != null) {
				sum = sum + deviation;
			}else {
				sum = sum + X;
			}
		}
		
		return daysCounter;
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
