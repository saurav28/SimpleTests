package org.saurav.simpletests.io;

import java.io.File;

import java.io.IOException;



import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Utility class to write data to xls file. Since this class is implementing
 * {@link AutoCloseable} interface, you can use the instance of this class in
 * try-resource statement. If you are not using try-resource statement, you must
 * call the {@link XLSWriteUtils#close()} metod explicitly.
 * 
 * @author Saurav Sarkar
 * @version 1
 * @since 1
 * 
 * @see XLSException
 * @see XLSReadUtils
 *
 */
public class XLSWriteUtils implements AutoCloseable {
	static WorkbookSettings workbookSettings = new WorkbookSettings();

	private static WritableCellFormat cellFormat = new WritableCellFormat();
	static {
		try {
			cellFormat.setAlignment(Alignment.LEFT);
			cellFormat.setWrap(true);
			workbookSettings.setEncoding("UTF-8");
			// cellFormat.setBackground(Colour.ROSE);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private WritableWorkbook workbook;

	public XLSWriteUtils(String path) {

		if (path == null || path.isEmpty()) {
			throw new XLSException("Can't create an xls sheet: file name is null or empty.");
		}

		try {
			workbook = Workbook.createWorkbook(new File(path), workbookSettings);
		} catch (IOException e) {
			throw new XLSException(e);
		}
	}

	public void createSheet(String sheetName, int index) {
		workbook.createSheet(sheetName, index);
	}

	public void writeData(WritableSheet sheet, int column, int row, String data) {
		Label label = new Label(column, row, data, cellFormat);
		try {
			sheet.addCell(label);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeData(String sheetName, int column, int row, String data, int rowHeight, int columnWidth)
			throws RowsExceededException {
		WritableSheet sheet = workbook.getSheet(sheetName);
		sheet.setRowView(row, rowHeight);
		sheet.setColumnView(column, columnWidth);

		writeData(sheet, column, row, data);
	}

	public void writeData(int sheetIndex, int column, int row, String data) {
		writeData(workbook.getSheet(sheetIndex), column, row, data);
	}

	public void close() {
		try {
			workbook.write();
			workbook.close();
		} catch (Exception e) {

		}
	}
}
