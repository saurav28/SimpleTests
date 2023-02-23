package org.saurav.simpletests.io;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Utility class to read the xls sheet.
 * 
 * @author Saurav Sarkar
 * @version 1
 * @since 1
 * 
 * @see XLSException
 * @see XLSWriteUtils
 *
 */
public class XLSReadUtils {

	private Workbook workbook = null;

	public XLSReadUtils(String path) {
		if (path == null || path.isEmpty()) {
			throw new XLSException("XLS sheet file is empty or null");
		}

		try {
			workbook = Workbook.getWorkbook(new File(path));
		} catch (IOException ex) {
			throw new XLSException(ex);
		} catch (BiffException ex) {
			throw new XLSException(ex);
		}
	}
	
	public int getNoOfSheets() {

		return workbook.getNumberOfSheets();
	}

	public void closeWorkbook() {

		workbook.close();
	}

	public Sheet getSheet(int index) {

		if (index >= workbook.getNumberOfSheets())
			throw new XLSException("XLS file don't  have " + index + " sheet");

		return workbook.getSheet(index);
	}

	public Sheet getSheet(String name) {

		Sheet sheet = workbook.getSheet(name);
		if (sheet == null)
			throw new XLSException("No xls sheet with given name : " + name);
		return sheet;
	}

	public String[] getSheetNames() {

		return workbook.getSheetNames();
	}

	public int getNumberOfRows(Sheet sheet) {

		return sheet.getRows();
	}

	public int getNumberOfColumns(Sheet sheet) {

		return sheet.getColumns();
	}

	public int getNumberOfRows(int sheetIndex) {
		Sheet sheet = getSheet(sheetIndex);
		return getNumberOfRows(sheet);
	}

	public int getNumberOfRows(String sheetName) {
		Sheet sheet = getSheet(sheetName);
		return getNumberOfRows(sheet);
	}

	public int getNumberOfColumns(int sheetIndex) {
		Sheet sheet = getSheet(sheetIndex);
		return getNumberOfColumns(sheet);
	}

	public int getNumberOfColumns(String sheetName) {
		Sheet sheet = getSheet(sheetName);
		return getNumberOfColumns(sheet);
	}

	public Cell getCell(int sheetIndex, int column, int row) {
		Sheet sheet = getSheet(sheetIndex);
		return getCell(sheet, column, row);
	}

	public Cell getCell(String sheetName, int column, int row) {
		Sheet sheet = getSheet(sheetName);
		return getCell(sheet, column, row);

	}

	public Cell getCell(Sheet sheet, int column, int row) {
		int actualColumns = sheet.getColumns() - 1;
		int actualRows = sheet.getRows() - 1;

		if (column > actualColumns)
			throw new XLSException(
					"Column not exist. actual columns : " + actualColumns + ", requested column : " + column);

		if (row > actualRows)
			throw new XLSException("Row not exist. actual rows : " + actualRows + ", requested row : " + row);

		return sheet.getCell(column, row);
	}

	public Cell getCell(int sheetIndex, String loc) {
		Sheet sheet = getSheet(sheetIndex);
		return getCell(sheet, loc);
	}

	public Cell getCell(String sheetName, String loc) {
		Sheet sheet = getSheet(sheetName);
		return getCell(sheet, loc);
	}

	public Cell getCell(Sheet sheet, String loc) {
		return sheet.getCell(loc);
	}

	public List<Cell> getEntireRow(int sheetIndex, int row) {
		Sheet sheet = getSheet(sheetIndex);
		return getEntireRow(sheet, row);
	}

	public List<Cell> getEntireRow(String sheetName, int row) {
		Sheet sheet = getSheet(sheetName);
		return getEntireRow(sheet, row);
	}

	public List<Cell> getEntireRow(Sheet sheet, int row) {
		int totalRows = sheet.getRows() - 1;
		if (row > totalRows)
			throw new XLSException("Row not exist. actual rows : " + totalRows + ", requested row : " + row);
		List<Cell> list = new ArrayList<Cell>();

		for (int column = 0; column < sheet.getColumns(); column++) {
			list.add(sheet.getCell(column, row));
		}
		return list;
	}

	public List<Cell> getEntireColumn(int sheetIndex, int column) {
		Sheet sheet = getSheet(sheetIndex);
		return getEntireColumn(sheet, column);
	}

	public List<Cell> getEntireColumn(String sheetName, int column) {
		Sheet sheet = getSheet(sheetName);
		return getEntireColumn(sheet, column);
	}

	public List<Cell> getEntireColumn(Sheet sheet, int column) {
		int totalColumns = sheet.getColumns() - 1;
		if (column > totalColumns)
			throw new XLSException(
					"Column not exist. actual columns : " + totalColumns + ", requested column : " + column);

		List<Cell> list = new ArrayList<Cell>();

		for (int row = 0; row < sheet.getRows(); row++) {
			list.add(sheet.getCell(column, row));
		}
		return list;
	}

	public List<List<Cell>> getSheetData(int sheetIndex) {
		Sheet sheet = getSheet(sheetIndex);
		return getSheetData(sheet);
	}

	public List<List<Cell>> getSheetData(String sheetName) {
		Sheet sheet = getSheet(sheetName);
		return getSheetData(sheet);
	}

	public List<List<Cell>> getSheetData(Sheet sheet) {
		List<List<Cell>> data = new ArrayList<List<Cell>>();
		int columns = sheet.getColumns();
		int rows = sheet.getRows();

		for (int row = 0; row < rows; row++) {
			List<Cell> list = new ArrayList<Cell>();
			for (int column = 0; column < columns; column++) {
				list.add(sheet.getCell(column, row));
			}
			data.add(list);
		}
		return data;
	}

	public Map<Integer, List<List<Cell>>> getAllSheetsDataIndexAsKey() {

		Map<Integer, List<List<Cell>>> map = new HashMap<Integer, List<List<Cell>>>();

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			List<List<Cell>> list = getSheetData(i);
			map.put(i, list);
		}

		return map;
	}

	public Map<String, List<List<Cell>>> getAllSheetsDataSheetNameAsKey() {

		Map<String, List<List<Cell>>> map = new HashMap<String, List<List<Cell>>>();
		String[] indexNames = workbook.getSheetNames();

		for (String sheetName : indexNames) {
			map.put(sheetName, getSheetData(sheetName));
		}
		return map;
	}

	Map<Sheet, List<List<Cell>>> getAllSheetsDataSheetAsKey() {

		Map<Sheet, List<List<Cell>>> map = new HashMap<Sheet, List<List<Cell>>>();

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheet(i);
			List<List<Cell>> list = getSheetData(i);
			map.put(sheet, list);
		}

		return map;
	}
}