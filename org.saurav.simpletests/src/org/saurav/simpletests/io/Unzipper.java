package org.saurav.simpletests.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Unzipper {

	public static void main(String a[]) {
		String fileZip = "/Users/i054564/Downloads/IMS+103401633+BD0000027+Belaruskali+zip.zip";
		File destDir = new File("/Users/i054564/Documents/UnzipTest");
		try {
			//extractFolder(fileZip);
			extractFolderUsingZipInputStream(fileZip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static public void extractFolder(String zipFile) throws ZipException, IOException {
		System.out.println(zipFile);
		int BUFFER = 2048;
		Charset CP866 = Charset.forName("CP866");
		File file = new File(zipFile);

		ZipFile zip = new ZipFile(file, CP866);
		ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
		String newPath = zipFile.substring(0, zipFile.length() - 4);
		BufferedOutputStream dest = null;
		BufferedInputStream is =null;
		new File(newPath).mkdir();
		Enumeration zipFileEntries = zip.entries();

		// Process each entry
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
			String currentEntry = entry.getName();
			File destFile = new File(newPath, currentEntry);
			// destFile = new File(newPath, destFile.getName());
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			if (!entry.isDirectory()) {
				is = new BufferedInputStream(zip.getInputStream(entry));
				int currentByte;
				// establish buffer for writing file
				byte data[] = new byte[BUFFER];

				// write the current file to disk
				FileOutputStream fos = new FileOutputStream(destFile);
				 dest = new BufferedOutputStream(fos, BUFFER);

				// read and write until last byte is encountered
				while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, currentByte);
				}
				dest.flush();
				dest.close();
				is.close();
			}

			if (currentEntry.endsWith(".zip")) {
				// found a zip file, try to open
				extractFolder(destFile.getAbsolutePath());
			}
		}
	}

	static public void extractFolderUsingZipInputStream(String zipFile) throws ZipException, IOException {
		System.out.println(zipFile);
		int BUFFER = 2048;
		Charset CP866 = Charset.forName("CP866");
		File file = new File(zipFile);
		BufferedOutputStream dest = null;
		BufferedInputStream is =null;
		ZipInputStream zis = null;
		try {
			// ZipFile zip = new ZipFile(file, CP866);
			
			zis = new ZipInputStream(new FileInputStream(file));
			String newPath = zipFile.substring(0, zipFile.length() - 4);

			new File(newPath).mkdir();
			// Enumeration zipFileEntries = zip.entries();

			// Process each entry
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				// grab a zip file entry
				// ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
				
				String currentEntry = entry.getName();
				System.out.println("current entry" + currentEntry);
				File destFile = new File(newPath, currentEntry);
				// destFile = new File(newPath, destFile.getName());
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();

				if (!entry.isDirectory()) {
					is = new BufferedInputStream(zis);
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					dest = new BufferedOutputStream(fos, BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}

				}

				if (currentEntry.endsWith(".zip")) {
					// found a zip file, try to open
					extractFolderUsingZipInputStream(destFile.getAbsolutePath());
				}
				

			}
		} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		 finally {
			 System.out.println("Executing finally");
			zis.close();
			dest.flush();
			dest.close();
			is.close();
		}

	}
}
