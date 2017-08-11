package com.actiTime.testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class ExcelManager {

	private File file;
	private FileInputStream fis;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private XSSFRow row;

	private static final String excelFilePath = "./src/test/resources/excelData/";
	private static final String inputFileName = "input.xlsx";

	public static ExcelManager excelManager = null;

	private ExcelManager() {
		initWorkbook();
	}
	
	//synchronized will ensure at a time only one thread will access this method,at a time its locked by a thread
	public synchronized static ExcelManager getInstance() {
		if(excelManager==null)
			excelManager = new ExcelManager();
		return excelManager;
	}

	public void initWorkbook() {

		try {
			file = new File(excelFilePath + "/" + inputFileName);
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			Reporter.log("---Excel workbook initialized---", true);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public String getExcelData(int sheetIndex, int row, int col) {
		sheet = wb.getSheetAt(sheetIndex);
		cell = sheet.getRow(row).getCell(col);
		String cellData =null;
		
		try {
			if(cell.getCellType() == cell.CELL_TYPE_STRING)
			{
			 cellData = cell.getStringCellValue().trim();
			 System.out.println("CellValue in Excel is String: "+cellData);
			}
			
			else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC)
			{
				if(DateUtil.isCellDateFormatted(cell)) //excel stores date as numbers internally
				{
					cellData = String.valueOf(new SimpleDateFormat("MM/dd/yyyy").format(cell.getDateCellValue()));
					System.out.println("CellValue in Excel is Date: "+cellData);
				}
				else
				{
					cellData = String.valueOf(cell.getNumericCellValue()).trim();
					System.out.println("CellValue in Excel is Numeric: "+cellData);
				}
			}
				
			else if(cell.getCellType() == cell.CELL_TYPE_BLANK)
			{
				cellData = "";
				System.out.println("CellValue in Excel is blank: "+cellData);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cellData;
	}

	public int getRowCount(int sheetIndex) {
		sheet = wb.getSheetAt(sheetIndex);
		return sheet.getPhysicalNumberOfRows();
	}

	public Object[][] getArrayOfObject(int sheetIndex) {

		int rows = getRowCount(sheetIndex);
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] arrayOfObject = new Object[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				arrayOfObject[i][j] = getExcelData(sheetIndex, i, j);
	
		//below code works when sheet has first row as headings and we do not want to read headings
/*		Object[][] arrayOfObject = new Object[rows-1][cols];
		for (int i = 1; i < rows; i++)
			for (int j = 0; j < cols; j++)
				arrayOfObject[i-1][j] = getExcelData(sheetIndex, i, j);*/

		return arrayOfObject;

	}

	public void printExcelDataWhenNoOfCellsNotSameInAllRows(int sheetIndex ) {

		int rows = getRowCount(sheetIndex);

		for (int i = 0; i < rows; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) 
				System.out.println(row.getCell(j).getStringCellValue()+"|| ");

		}

	}
	
	public ArrayList<ArrayList<String>> getListOfExcelData(int sheetIndex) {
		sheet = wb.getSheetAt(sheetIndex);	
		ArrayList<ArrayList<String>> listofcellList =new ArrayList<ArrayList<String>> ();
		Iterator<Row> rowItr = sheet.iterator();
		
		while(rowItr.hasNext())
		{
			Row row = rowItr.next();
			Iterator<Cell> cellItr = row.iterator();
			ArrayList<String> cellList =new ArrayList<String>();
			while(cellItr.hasNext())
			{
				String cellData=null;
				Cell cell = cellItr.next();
				
				 switch (cell.getCellType()) 
                 {
                     case Cell.CELL_TYPE_NUMERIC:
                    	 if(DateUtil.isCellDateFormatted(cell)) //excel stores date as numbers internally
         				{
         					cellData = String.valueOf(new SimpleDateFormat("MM/dd/yyyy").format(cell.getDateCellValue()));
         					System.out.println("CellValue in Excel is Date: "+cellData);
         				}
         				else
         				{
         					cellData = String.valueOf(cell.getNumericCellValue()).trim();
         					System.out.println("CellValue in Excel is Numeric: "+cellData);
         				}

                         break;
                         
                     case Cell.CELL_TYPE_STRING:
                    	 cellData=String.valueOf(cell.getStringCellValue());
                         break;
                 }
				 cellList.add(cellData);
				 
			}
			listofcellList.add(cellList);
				
			
		}
			
		for(ArrayList<String> row: listofcellList)
		{
			for(String cell: row)
			{
				System.out.println("List of List Data:"+cell);
			}
		}
		return listofcellList;
	}
	
	public ArrayList<EmployeeData> getEmployeeRecord(int sheetIndex) {
		sheet = wb.getSheetAt(sheetIndex);	
		ArrayList<EmployeeData> empList =new ArrayList<EmployeeData> ();
		Iterator<Row> rowItr = sheet.iterator();
		
		while(rowItr.hasNext())
		{
			Row row = rowItr.next();
			Iterator<Cell> cellItr = row.iterator();
			EmployeeData e= new EmployeeData();
			int colcounter=0;
			while(cellItr.hasNext())
			{
				double cellnum=0;
				String celltext=null;
				Date celldate=null;
				Cell cell = cellItr.next();
				
				 switch (cell.getCellType()) 
                 {
                     case Cell.CELL_TYPE_NUMERIC:
                    	 if(DateUtil.isCellDateFormatted(cell)) //excel stores date as numbers internally
         				{
                    		 celldate = cell.getDateCellValue();
         					System.out.println("CellValue in Excel is Date: "+celldate);
         				}
         				else
         				{
         					cellnum = cell.getNumericCellValue();
         					System.out.println("CellValue in Excel is Numeric: "+cellnum);
         				}

                         break;
                         
                     case Cell.CELL_TYPE_STRING:
                    	 celltext=String.valueOf(cell.getStringCellValue());
                         break;
                 }
				 if(colcounter==0)
				 e.setFirstName(celltext);
				 else if(colcounter==1)
					 e.setLastName(celltext);
				 else if(colcounter==2)
					 e.setAge((int)cellnum);
				 else if(colcounter==3)
					 e.setDob(celldate);
				 else if(colcounter==4)
					 e.setSalary(cellnum);
				 else if(colcounter==5)
					 e.setAddress(celltext);
					 
				 colcounter++; 
			}
			empList.add(e);
				
		}
			
		for(EmployeeData ed : empList)
		{
				System.out.println("employee data are:");
				System.out.println(ed.getFirstName()+"== "+ed.getLastName()+"== "+ed.getAge()+" =="+ed.getDob()+"== "+ed.getSalary()+"== "+ed.getAddress());
			
		}
	
	
	return empList;
	}
}
