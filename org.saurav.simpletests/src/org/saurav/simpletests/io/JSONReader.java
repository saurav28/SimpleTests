package org.saurav.simpletests.io;

import java.io.StringReader;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.io.File;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.json.JSONArray;
import org.jsontocsv.parser.JSONFlattener;
import org.jsontocsv.writer.CSVWriter;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class JSONReader {

	public static void main(String a[]) {

		String repositoryJSONData = "[\n" + "    {\n"
				+ "        \"tenantId\": \"1456c6d5-bed9-48d4-88f7-0b788e70be05\",\n"
				+ "        \"id\": \"5aacd1f2ebd72fc052343595\",\n" + "        \"contentsize\": 6,\n"
				+ "        \"metadatasize\": 2512,\n"
				+ "        \"uniqueName\": \"w99fc3b9d.w60f38547.doc_service_repository\"\n" + "    },\n" + "    {\n"
				+ "        \"tenantId\": \"9518debe-9317-4f62-ac68-d0b438699d8e\",\n"
				+ "        \"id\": \"5aacd1f2ebd72fc052343595\",\n" + "        \"contentsize\": 0,\n"
				+ "        \"metadatasize\": 496,\n"
				+ "        \"uniqueName\": \"w99fc3b9d.w60f38547.doc_service_repository\"\n" + "    },\n" + "    {\n"
				+ "        \"tenantId\": \"fce76804-13e7-44ae-935b-0dc0324376cd\",\n"
				+ "        \"id\": \"53abb91800d42fc0b64f9085\",\n" + "        \"contentsize\": 14693223,\n"
				+ "        \"metadatasize\": 926336,\n"
				+ "        \"uniqueName\": \"w99fc3b9d.w749926aa.s4_hl_cmis_api\"\n" + "    }" + "]";

		JsonReader repositoryReader = Json.createReader(new StringReader(repositoryJSONData));
		
		JSONReader jsonReader = new JSONReader();
		jsonReader.readJSON(repositoryReader);

	}
	
	public  void readJSON(JsonReader jsonReader) {
		
		

		JsonArray repositoryArray = jsonReader.readArray();
		
		WritableWorkbook repositoryReportBook = null;
		/*
		 * Parse a JSON String and convert it to CSV
		 */
		
		
		// List<Map<String, String>> flatJson = JSONFlattener.parseJson(repositoryArray.toString().toString());
		// Using the default separator ','
		// If you want to use an other separator like ';' or '\t' use
		// CSVWriter.getCSV(flatJSON, separator) method
		//CSVWriter.writeToFile(CSVWriter.getCSV(flatJson), "files/sample_string.csv");
		
		try {
			repositoryReportBook = Workbook.createWorkbook(new File("files/sample_string.xls"));
			
			WritableSheet repositorySheet = JExcelHelper.createWrtiableSheet(repositoryReportBook, "Canary", 0);
		
			JExcelHelper.addData(repositorySheet, 0, 0, "uniqueName");
			JExcelHelper.addData(repositorySheet, 1, 0, "contentsize");
			JExcelHelper.addData(repositorySheet, 2, 0, "metadatasize");

		for (int i = 0; i < repositoryArray.size(); i++) {
			//System.out.println("Content Size   : " + repositoryArray.getJsonObject(i).getInt("contentsize"));
			JExcelHelper.addData(repositorySheet, 0, i+1, repositoryArray.getJsonObject(i).getInt("contentsize"));
			//System.out.println("Metadata Size   : " + repositoryArray.getJsonObject(i).getInt("metadatasize"));
			JExcelHelper.addData(repositorySheet, 1, i+1, repositoryArray.getJsonObject(i).getInt("metadatasize"));
			//System.out.println("Unique Name   : " + repositoryArray.getJsonObject(i).getString("uniqueName"));
			JExcelHelper.addData(repositorySheet, 2, i+1, repositoryArray.getJsonObject(i).getString("uniqueName"));
		}
		
			repositoryReportBook.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {

		jsonReader.close();
		
		try {
			repositoryReportBook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
