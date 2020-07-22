package org.saurav.simpletests.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * To test the split and merge files using different techniques in Java.
 * 
 * 1. Using plain byte copying/writing 
 * 2. Using SequenceInputStream
 * 
 * @author Saurav Sarkar
 *
 */
public class ConcatStreams {

	public static void main(String a[]) {

		ConcatStreams concatStreams = new ConcatStreams();

		File mergedFileOutput = new File(
				"<path to the output file>");
		List<File> splittedFiles = concatStreams.splitFile(new File(
				"<path to the input file>"));
		// concatStreams.mergeFiles(splittedFiles, mergedFileOutput);
		// concatStreams.mergeFilesUsingStream(splittedFiles, mergedFileOutput);
		concatStreams.mergeUsingSequenceInputStream(splittedFiles, mergedFileOutput);

	}

	private List<File> splitFile(File f) {
		int partCounter = 1;// I like to name parts from 001, 002, 003, ...
		// you can change it to 0 if you want 000, 001, ...
		List<File> splittedFiles = new ArrayList<File>();
		int sizeOfFiles = (1024 * 1024) / 2;// 1MB
		byte[] buffer = new byte[sizeOfFiles];

		String fileName = f.getName();

		// try-with-resources to ensure closing stream
		try (FileInputStream fis = new FileInputStream(f); BufferedInputStream bis = new BufferedInputStream(fis)) {

			int bytesAmount = 0;
			while ((bytesAmount = bis.read(buffer)) > 0) {
				// write each chunk of data into separate file with different number in name
				String filePartName = String.format("%s.%03d", fileName, partCounter++);
				File newFile = new File(f.getParent(), filePartName);
				try (FileOutputStream out = new FileOutputStream(newFile)) {
					out.write(buffer, 0, bytesAmount);
				}
				splittedFiles.add(newFile);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return splittedFiles;
	}

	public void mergeFiles(List<File> files, File into) throws IOException {

		try (FileOutputStream fos = new FileOutputStream(into);
				BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
			for (File f : files) {
				Files.copy(f.toPath(), mergingStream);
			}
		}
	}
		
	/**
	 *  Merging the files using explicit byte copying
	 * @param files
	 * @param into
	 * @throws IOException
	 */
	private void mergeFilesUsingStream(List<File> files, File into) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(into);
				BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
			for (File f : files) {
				byte[] buffer = new byte[256];
				int data;
				FileInputStream input = new FileInputStream(f);
				data = input.read(buffer);
				while (data != -1) {
					String str = new String(buffer, "UTF-8");
					// System.out.println(str);
					fos.write(buffer, 0, data);
					data = input.read(buffer);

				}

			}
		}
	}

	/** Merging the files using SequenceInputStream
	 * 
	 * @param files
	 * @param into
	 */
	private void mergeUsingSequenceInputStream(List<File> files, File into) {

		FileOutputStream fos = null;
		SequenceInputStream sequenceInputStream = null;
		try {
			fos = new FileOutputStream(into);
			// FileInputStream inputStream = new File
			Vector<InputStream> streams = new Vector<InputStream>();

			for (File file : files) {
				streams.add(new FileInputStream(file));
			}
			sequenceInputStream = new SequenceInputStream(streams.elements());
			byte[] buffer = new byte[256];
			int data;

			data = sequenceInputStream.read(buffer);
			while (data != -1) {
				String str = new String(buffer, "UTF-8");
				// System.out.println(str); // uncomment if you want to see the content in the console
				fos.write(buffer);
				data = sequenceInputStream.read(buffer);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				sequenceInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
