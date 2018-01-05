package com.core.utils;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.test.testng.FramworkConfig;




public class ExcelTestData  {

	static private InputStream excelFileToRead;
	static private Workbook workbook;
	static private Sheet sheet;
	static private int rowStart;
	static private int rowEnd;
	static private Row row;
	static private Cell prevCell;
	static private Cell currentCell;
	static private Cell nextCell;
	static private String key = new String();
	static private String value = new String();
	static private Properties readLocatorPropertiesFile = new Properties();
	static private Properties putLocatorsOnProperties = new Properties();
	static private Properties readTestPropertiesFile = new Properties();
	static private Properties putTestDataOnProperties = new Properties();
	static private String methodName= new String();
	
	
	
	
/*	static
	{
		Assert.assertTrue(getExcelWorkbook(), "log msg could not create excel file");
		readLocatorPropertiesFile.clear();
		readLocatorPropertiesFile = getAllLocatorsInProerties();
		readTestPropertiesFile.clear();
		readTestPropertiesFile = getAllTestDataInProperties();
	}
*/	
	public static void executeExcelFile(){
		Assert.assertTrue(getExcelWorkbook(), "log msg could not create excel file");
		readLocatorPropertiesFile.clear();
		readLocatorPropertiesFile = getAllLocatorsInProerties();
		readTestPropertiesFile.clear();
		readTestPropertiesFile = getAllTestDataInProperties();
	}
	
	public static void setMethodName(String methodNameFromBeforeMethod){
		methodName = methodNameFromBeforeMethod;
	}
	
	
	private static boolean	 getExcelWorkbook() { 
		
		try {
			
			excelFileToRead = ExcelTestData.class.getClassLoader().getResourceAsStream(FramworkConfig.globalConfig.getProperty("ExcelFile"));//new FileInputStream("resources/testLocators.xls"); // "/testLocators.xls" this should come from config file
		//	File f = new File("resources/testLocators.xls");
			System.out.println("Creating WorkBook ...");
			workbook = WorkbookFactory.create(excelFileToRead);
			System.out.println("Workbook Created. ");
			return true;
			// need to give name of sheet from global config file
			//sheet = workbook.getSheet(sheetName);
		} 
		catch (Exception e) {
			System.err.println("Not able to create Workbook due to : "+e);
			System.err.println(e.getMessage());
			Thread.currentThread().stop();
			return false;
			
		}
	}
	
	
	private static Properties getAllTestDataInProperties(){
		try{
		System.out.println("Getting Test Data excel sheet...");// need to add " + excel sheet name 
		
		
		//here it will take excel sheet name from global config file
		sheet = workbook.getSheet(FramworkConfig.globalConfig.getProperty("DataSheet"));
		System.out.println("TestData Sheet Created.");
		
		getFirstAndLastRowsNumber(sheet);
		
		int counter = 0;
		
		for (int rowNumber = rowStart; rowNumber <= rowEnd; rowNumber++) {
			
			row = sheet.getRow(rowNumber);
			
			if(row==null){
				//still need to complete this part
				
				continue;
			}
			
			 int Lastcell= row.getLastCellNum()-1;// this need to check
			 
			 Properties innerprop = new Properties();
			 key=null;
             for (int cellNumber=0;cellNumber<=Lastcell;cellNumber++) {
                 Cell cell = row.getCell(cellNumber,Row.CREATE_NULL_AS_BLANK);
                 
                 if(cell.getColumnIndex()==0 && cell.getCellType()==Cell.CELL_TYPE_BLANK){
                	 break;
                 }
              
                 else if(cell.getColumnIndex()==0 && cell.getCellType()!=Cell.CELL_TYPE_BLANK){
                	 key=cell.toString();
                   continue;
                 }
                 else if (cell.getCellType()==Cell.CELL_TYPE_BLANK){
                	continue;
                 }
                 else if(!cell.toString().contains("=") || cell.toString().endsWith("=") || cell.toString().startsWith("=")){
                	
                     counter++;
                     continue;
                 }
                 
                 else {
                     
                     String[] temp = cell.toString().split("=");
                  
                     if(temp[0]!=null && temp[1]!=null){
                     	 innerprop.put(temp[0], temp[1]);
                         
                     }
                     }    
                 
             }
             
             
             
             if(key!=null && innerprop!=null){
              
                 putTestDataOnProperties.put(key, innerprop);
             
             }

		}
		if(counter>=1){
			System.out.println("Total "+counter+" no. of element missing in test data, could not proceed with missing elements");
			throw new NullPointerException();
			}
			if (putTestDataOnProperties.isEmpty()) {
				System.out.println("Test Data Properties file is empty...");
				throw new NullPointerException();
			}
			
	 return putTestDataOnProperties;
		}
		catch (NullPointerException e) {
			
			System.out.println(e.getMessage());
			Thread.currentThread().stop();
			return null;
		}
	}
	
	//Need to provide sheet name from global config file to excel sheet
	private static Properties getAllLocatorsInProerties() {
		try {
			System.out.println("Getting locator excel sheet...");// need to add " + excel sheet name 
			
			//here it will take excel sheet and excel file name from global config file 
		sheet= workbook.getSheet(FramworkConfig.globalConfig.getProperty("LocatorSheet"));
		System.out.println("locator Sheet Created.");
		
		getFirstAndLastRowsNumber(sheet);
		int counter = 0;
		for (int rowNumber = rowStart; rowNumber <= rowEnd; rowNumber++) {
			
			row = sheet.getRow(rowNumber);
			
			if(row==null){
				//still need to complete this part
				//System.out.println("row found row as null");
				continue;
			}
			
			for (int columnNumber = 0; columnNumber < 2; columnNumber++) {
				
				if(columnNumber==0){
					prevCell = row.getCell(columnNumber,Row.CREATE_NULL_AS_BLANK);
				}
				else prevCell = row.getCell(columnNumber-1,Row.CREATE_NULL_AS_BLANK);
				currentCell =row.getCell(columnNumber,Row.CREATE_NULL_AS_BLANK);
				nextCell = row.getCell(columnNumber+1,Row.CREATE_NULL_AS_BLANK);
				
				if (currentCell.getCellType()== Cell.CELL_TYPE_BLANK) {
					
					if (currentCell.getColumnIndex()==0 && nextCell.getCellType()!=Cell.CELL_TYPE_BLANK) {
									counter++;
								//	throw new NullPointerException();
					}
					
					else if(currentCell.getColumnIndex()==1 && prevCell.getCellType()!=Cell.CELL_TYPE_BLANK){
					
						counter++;
						//throw new NullPointerException();
					}
					
					key=null;
					value=null;
					
				}
				
				else {
					if (currentCell.getColumnIndex()==0) {
						key = currentCell.toString();
					}
					 else {
						 
						value = currentCell.toString();
					}
					
				}
				if(key!=null && value != null){
					putLocatorsOnProperties.put(key, value);
				}
					
				
			}
			
		}
		if(counter>1){
		System.out.println("Total "+counter+" number of element missing in locator sheet, Its important to avoid leaving blank cell in between");
		//throw new NullPointerException();
		}
		if (putLocatorsOnProperties.isEmpty()) {
			System.out.println("Locators Properties file is empty...");
			throw new NullPointerException();
		}
		
		return putLocatorsOnProperties;	
		}
		catch (NullPointerException e) {
			
			System.out.println(e.getMessage());
			Thread.currentThread().stop();
			return null;
		}
		}


	


	private static void getFirstAndLastRowsNumber(Sheet currentSheet) {
		System.out.println("Getting rows number.");
		if(currentSheet==null){
			System.out.println("not able to get Rows as sheet is null");
		}
		rowStart = currentSheet.getFirstRowNum()+1;
		
		rowEnd = currentSheet.getLastRowNum();
		int endRowTemp = rowEnd+1;
		System.out.println("rows created starting row is at :"+rowStart+", End row is :"+endRowTemp);
		
	}
	
	
	public static String getTestLocators(String name) {
		try{
			
//			if(name.isEmpty()){
//				System.out.println("No test locator provided...");
//				throw new NullPointerException();
//			}
			
		String testLocators = readLocatorPropertiesFile.getProperty(name);
		if(testLocators==null){
			System.out.println("Could not find test locator for "+name+" ...");
			throw new NullPointerException();
		}
		else return testLocators;
		}
		catch (NullPointerException e) {
			System.out.println(e.toString());
			Thread.currentThread().stop();
			return null;
		}
	}
	
	
	public static By getTestLocator(String elementName) {
		try{
			
			if(elementName.isEmpty()){
				System.out.println("No test locator provided...");
				throw new NullPointerException();
			}
			
		String testLocators = readLocatorPropertiesFile.getProperty(elementName);
		

		if (testLocators.startsWith("//")) {
			return By.xpath(testLocators);		
		} else if(testLocators.startsWith("xpath=")) {	
			String xpath = testLocators.substring("xpath=".length());
			return By.xpath(xpath);
		} else if (testLocators.startsWith("link=")) {
			String link = testLocators.substring("link=".length());
			return By.linkText(link);
		} else if (testLocators.startsWith("id=")) {
			String id = testLocators.substring("id=".length());
			return By.id(id);
		} else if (testLocators.startsWith("name=")) {
			String name = testLocators.substring("name=".length());
			return By.name(name);
		} else if (testLocators.startsWith("css=")) {
			String css = testLocators.substring("css=".length());
			return By.cssSelector(css);
		} else {
			if (testLocators.startsWith("tag=")) {
				String tag = testLocators.substring("tag=".length());
				return By.cssSelector(tag);
			}
			else {
				System.out.println("Could not get the type of element");
				throw new NullPointerException();
			} 
		}
		}
		catch (NullPointerException e) {
			System.out.println(e.toString());
			Thread.currentThread().stop();
			return null;
		}
		
	}
	
	public static String getTestData(String name) {
		try{
			if(name.isEmpty()){
				System.out.println("No test data provided...");
				throw new NullPointerException();
			}
			if(methodName == null){
				System.out.println("Could not find any Method name to proceed for test data ...");
				throw new NullPointerException();
			}
			else if(!readTestPropertiesFile.containsKey(methodName)){
				System.out.println("Could not find Method name "+methodName+" in excel sheet to proceed for test data ...");
				throw new NullPointerException();
			}
			Properties inner = (Properties) readTestPropertiesFile.get(methodName);
		String testData = inner.getProperty(name);
		if(testData==null){
			System.out.println("Could not find \""+name+"\" in test data sheet");
			throw new NullPointerException();
		}
		 return testData;
		}
		catch (NullPointerException e) {
			System.out.println(e.toString());
			Thread.currentThread().stop();
			return null;
		}
	}
	
}
