package org.saurav.simpletests.io;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Using JExcel APIs
 * 
 * @author Saurav Sarkar
 *
 */
public class JExcelHelper {

	private static final String EXCEL_FILE_LOCATION = "/Users/i054564/Documents/Work/Temp/MyFirstExcel.xls";

	private WritableWorkbook testWorkbook = null;

	public static void main(String a[]) {

		JExcelHelper jExcelHelper = new JExcelHelper();
		jExcelHelper.createWorkbook("samplesheet");

	}
	
	public static void addData(WritableSheet sheet,int column, int row, String data) {
		Label label = new Label(column, row, data);
		try {
			sheet.addCell(label);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addData(WritableSheet sheet,int column, int row, int data) {
		Number number = new Number(column, row, data);
		try {
			sheet.addCell(number);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static WritableSheet createWrtiableSheet(WritableWorkbook workbook, String worksheetName, int index) {
		WritableSheet repositorySheet = workbook.createSheet(worksheetName, index);
		
		return repositorySheet;
	}

	private void createWorkbook(String worksheetName) {

		try {
			testWorkbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

			WritableSheet excelSheet = testWorkbook.createSheet(worksheetName, 0);

			Label label = new Label(0, 0, "Test Count");
			excelSheet.addCell(label);

			Number number = new Number(0, 1, 1);
			excelSheet.addCell(number);

			label = new Label(1, 0, "Result");
			excelSheet.addCell(label);

			label = new Label(1, 1, "Passed");
			excelSheet.addCell(label);

			number = new Number(0, 2, 2);
			excelSheet.addCell(number);

			label = new Label(1, 2, "Passed 2");
			excelSheet.addCell(label);

			testWorkbook.write();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (testWorkbook != null) {
				try {
					testWorkbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
