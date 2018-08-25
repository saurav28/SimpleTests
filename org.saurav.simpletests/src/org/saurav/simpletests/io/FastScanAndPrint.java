package org.saurav.simpletests.io;

import java.io.IOException;

/**
 * 
 * Motivation of this class is to check the faster I/O in java than the traditional ones
 * It is inpired from https://www.hackerearth.com/practice/notes/inputoutput-in-javascanner-bufferedreader-self-made-fast-io/
 *
 */
public class FastScanAndPrint {
	
	int data;
	
	public static void main (String a[]) {
		FastScanAndPrint fscp = new FastScanAndPrint();
		fscp.testScan();
		fscp.testPrint();
	}
	
	private void testScan() {
		Scan scan = new Scan();
		try {
			data = scan.scanInt();
			int x = scan.scanInt();
			int y =scan.scanInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testPrint() {
		Print print = new Print();
		try {
			print.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				print.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
