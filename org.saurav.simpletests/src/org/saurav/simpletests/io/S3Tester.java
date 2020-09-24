package org.saurav.simpletests.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;

/**
 * Test class to test S3.
 * 
 * Generates a file with random bytes and uploads into S3
 * 
 * S3 code are token from
 * https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/java/example_code/s3/src/main/java/aws/example/s3/LowLevelMultipartUpload.java
 * 
 * @author Saurav Sarkar
 *
 */
public class S3Tester {

	AmazonS3 s3Client;

	Regions clientRegion = Regions.DEFAULT_REGION;
	String bucketName = "sauravtesting";
	String stringObjKeyName = "sauravteststring";
	String fileObjKeyName = "sauravtestfile";
	String fileName = "*** Path to file to upload ***";
	InitiateMultipartUploadRequest initRequest;
	InitiateMultipartUploadResult initResponse;
	String  uploadId;
	List<PartETag> partETags = new ArrayList<PartETag>();

	public static void main(String a[]) {

		S3Tester s3Tester = new S3Tester();
		s3Tester.setupS3Bucket();

		// s3Tester.fileUpload("/Users/<xxxxx>/Downloads/COVD9060901.pdf");
		int fileSize =  10487857;
		//s3Tester.multiPartUpload(s3Tester.generateRandomFile(fileSize));
		
		//s3Tester.multiPartStreamUpload(s3Tester.generateRandomFile(fileSize),true);
		
		s3Tester.multiPartUploadFromFolderNew("/Users/<xxxxx>/Documents/Work/Temp/s3/testsource");
	}

	/**
	 * Upload a single file as multi part upload.
	 * Useful when the file size is known upfront.
	 */
	private void multiPartUpload(String filePath) {

		File file = new File(filePath);
		long contentLength = file.length();
		long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.
		// Create a list of ETag objects. You retrieve ETags for each object part
		// uploaded,
		// then, after each individual part has been uploaded, pass the list of ETags to
		// the request to complete the upload.
		try {
			List<PartETag> partETags = new ArrayList<PartETag>();

			// Initiate the multipart upload.
			InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, file.getName());
			InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);

			// Upload the file parts.
			long filePosition = 0;
			for (int i = 1; filePosition < contentLength; i++) {
				// Because the last part could be less than 5 MB, adjust the part size as
				// needed.
				partSize = Math.min(partSize, (contentLength - filePosition));
				System.out.println("part size" + partSize);
				UploadPartRequest uploadRequest = null;
				// Create the request to upload a part.
				if(partSize == 5 * 1024 * 1024 ) {
				uploadRequest = new UploadPartRequest().withBucketName(bucketName).withKey(file.getName())
						.withUploadId(initResponse.getUploadId()).withPartNumber(i).withFileOffset(filePosition)
						.withFile(file).withPartSize(partSize);
				}else {
					System.out.println("setting the last part flag");
					uploadRequest = new UploadPartRequest().withBucketName(bucketName).withKey(file.getName())
							.withUploadId(initResponse.getUploadId()).withPartNumber(i).withFileOffset(filePosition)
							.withFile(file).withPartSize(partSize).withLastPart(true);
					//is sending is last part mandatory
					
				}

				// Upload the part and add the response's ETag to our list.
				UploadPartResult uploadResult = s3Client.uploadPart(uploadRequest);
				partETags.add(uploadResult.getPartETag());

				filePosition += partSize;
			}
			System.out.println("etags size" + partETags.size());
			// Complete the multipart upload.
			CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, file.getName(),
					initResponse.getUploadId(), partETags);
			s3Client.completeMultipartUpload(compRequest);
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
	}
	
	/**
	 *  Uploading the content in stream
	 *  Useful in scenarios where length of the file is not known upfront
	 *  
	 *  This method is now optimized for calling multiple times for multiple files.
	 * @param filePath
	 */
	private void multiPartStreamUpload(String folderPath ,String filePath, boolean isLastPart, int partNumber) {
		
		System.out.println("the file path "  +filePath);
		File file = new File(filePath);
		//long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.
		// Create a list of ETag objects. You retrieve ETags for each object part
		// uploaded,
		// then, after each individual part has been uploaded, pass the list of ETags to
		// the request to complete the upload.
		try {

			long partSize = 5 * 1024 * 1024;
			FileInputStream inputStream = new FileInputStream(file);

			// Initiate the multipart upload.

			String objectName = "";
			if (folderPath.isEmpty() || folderPath == null) {
				objectName = file.getName();
			} else {
				objectName = folderPath;
			}

			if (initRequest == null) { //  this is required if the multi part stream upload is triggered externally
				initRequest = new InitiateMultipartUploadRequest(bucketName, objectName);
				initResponse = s3Client.initiateMultipartUpload(initRequest);
				uploadId = initResponse.getUploadId();
			}
			byte[] buffer = new byte[(int) partSize];
			int i = 1;
			int byteSize = inputStream.read(buffer);
			while (byteSize != -1) {
				// Upload the file parts.
				UploadPartRequest uploadRequest = null;
				// determine part number
				int partSeqNumber;
				if (partNumber == 0) {
					partSeqNumber = i; // if part number is not sent then consider be from the current loop
				} else {
					partSeqNumber = partNumber;
				}
				if (byteSize == partSize) {
					String md5hash = getMd5Hash(buffer, (int)partSize);
					uploadRequest = new UploadPartRequest().withBucketName(bucketName).withKey(objectName)
							.withUploadId(uploadId).withPartNumber(partSeqNumber).withMD5Digest(md5hash)
							.withInputStream(new ByteArrayInputStream(buffer)).withPartSize(partSize);

				} else {
					System.out.println("buffer size for the last part " + byteSize);
					String md5hash = getMd5Hash(buffer, byteSize);
					if (isLastPart) {
						uploadRequest = new UploadPartRequest().withBucketName(bucketName).withKey(objectName)
								.withUploadId(uploadId).withPartNumber(partSeqNumber).withMD5Digest(md5hash)
								.withInputStream(new ByteArrayInputStream(buffer)).withPartSize(byteSize)
								.withLastPart(true);
					} else {
						uploadRequest = new UploadPartRequest().withBucketName(bucketName).withKey(objectName)
								.withUploadId(uploadId).withPartNumber(partSeqNumber).withMD5Digest(md5hash)
								.withInputStream(new ByteArrayInputStream(buffer)).withPartSize(byteSize);
					}
				}
				// Upload the part and add the response's ETag to our list.
				UploadPartResult uploadResult = s3Client.uploadPart(uploadRequest);
				partETags.add(uploadResult.getPartETag());
				i++;
				byteSize = inputStream.read(buffer);
			}
			System.out.println("etags size " + partETags.size());
			// Complete the multipart upload.
			if (isLastPart) {
				CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, objectName,
						uploadId, partETags);
				s3Client.completeMultipartUpload(compRequest);
				initRequest = null; // make the references null for next complete file upload
				initResponse = null;
				partETags.clear();
			}
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Upload all the files in the folder with the folder name as the name of the file.
	 * All the files are uploaded as a single file.Each file is uploaded as a separate part.
	 * For this method to work each file should be of 5 MB because S3 expects the part of equals size.
	 * The last file/part should be of lesser than 5MB. 
	 * 
	 * If you send files of different sizes, then it will not work because the files/parts should be of same size
	 * except the last file/part
	 * @param folderPath
	 */
	private void multiPartUploadFromFolderNew(String folderPath) {
		List<File> files = FilleUtility.listFiles(folderPath);
		System.out.println("Number of files in the folder " + files.size());
		for (int i = 0; i < files.size(); i++) {

			if (files.size() - i == 1) {
				//send the last part as true because this is the last file
				multiPartStreamUpload("source", files.get(i).getPath(), true, i + 1);
			} else {
				multiPartStreamUpload("source", files.get(i).getPath(), false, i + 1);
			}
		}
	}
	
	
	/**
	 *
	 * @param folderPath
	 */
	private void multiPartUploadFromFolder(String folderPath) {
		
		List<File> files = FilleUtility.listFiles(folderPath);
		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			
			
			long contentLength = file.length();
			long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.
			// Create a list of ETag objects. You retrieve ETags for each object part
			// uploaded,
			// then, after each individual part has been uploaded, pass the list of ETags to
			// the request to complete the upload.
			try {
				List<PartETag> partETags = new ArrayList<PartETag>();

				// Initiate the multipart upload.
				InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, folderPath);
				InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);

				// Upload the file parts.
				long filePosition = 0;
				for (int i = 1; filePosition < contentLength; i++) {
					// Because the last part could be less than 5 MB, adjust the part size as
					// needed.
					partSize = Math.min(partSize, (contentLength - filePosition));
					System.out.println("part size" + partSize);
					UploadPartRequest uploadRequest = null;
					// Create the request to upload a part.
					if(partSize == 5 * 1024 * 1024 ) {
					uploadRequest = new UploadPartRequest().withBucketName(bucketName).withKey(folderPath)
							.withUploadId(initResponse.getUploadId()).withPartNumber(i).withFileOffset(filePosition)
							.withFile(file).withPartSize(partSize);
					}else {
						System.out.println("setting the last part flag");
						uploadRequest = new UploadPartRequest().withBucketName(bucketName).withKey(folderPath)
								.withUploadId(initResponse.getUploadId()).withPartNumber(i).withFileOffset(filePosition)
								.withFile(file).withPartSize(partSize).withLastPart(true);
						//is sending is last part mandatory
						
					}

					// Upload the part and add the response's ETag to our list.
					UploadPartResult uploadResult = s3Client.uploadPart(uploadRequest);
					partETags.add(uploadResult.getPartETag());

					filePosition += partSize;
				}
				System.out.println("etags size" + partETags.size());
				// Complete the multipart upload.
				CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, folderPath,
						initResponse.getUploadId(), partETags);
				s3Client.completeMultipartUpload(compRequest);
			} catch (AmazonServiceException e) {
				// The call was transmitted successfully, but Amazon S3 couldn't process
				// it, so it returned an error response.
				e.printStackTrace();
			} catch (SdkClientException e) {
				// Amazon S3 couldn't be contacted for a response, or the client
				// couldn't parse the response from Amazon S3.
				e.printStackTrace();
			}
			
		}
		
	}

	private void singleUpload() {

	}

	private void fileUpload(String fileName) {
		// ConcatStreams concatStreams = new ConcatStreams();
		// List<File> files = concatStreams.splitFile(file);

		try {
			// Upload a text string as a new object.
			PutObjectResult result = s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

			// Upload a file as a new object with ContentType and title specified.
			PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
			ObjectMetadata metadata = new ObjectMetadata();
			// metadata.setContentType("plain/text");
			metadata.addUserMetadata("title", "someTitle");
			request.setMetadata(metadata);
			s3Client.putObject(request);
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
	}

	/**
	 * Setup S3 bucket
	 */
	private void setupS3Bucket() {
		s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();

	}
	
	/**
	 * Generates a file with ramdom bytes
	 * @param size
	 * @return
	 */
	private String generateRandomFile(int size) {
		 	File file = null ;
			try {
				Random random = new Random();
				
				byte[] byteArray =  new byte[size];
				random.nextBytes(byteArray);

		         file = new File("/Users/<xxxxx>/Documents/Work/Temp/s3/randomFile");
		         //if file exists then don't create a new file
		         if(!file.exists()) {
		         BufferedOutputStream bofOutputStream = new BufferedOutputStream(new FileOutputStream(file));
		         bofOutputStream.write(byteArray);
		         System.out.println("file generated");
		         }
		     
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return file.toString();
	}
	
	
	 private String getMd5Hash(byte[] chunk, int length) {
		 	String hash = "";
		    try {
		      MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		      md5Digest.update(chunk, 0, length);
		      byte[] hashedBytes = md5Digest.digest();

		      byte[] baseHash = Base64.getEncoder().encode(hashedBytes);
		      hash = new String(baseHash, Charset.forName("UTF-8"));
		      return hash;
		    }
		    catch (NoSuchAlgorithmException e) {
		      e.printStackTrace();
		    }
		    return  hash;
		  }

}
