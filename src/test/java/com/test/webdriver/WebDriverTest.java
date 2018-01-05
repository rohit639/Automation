package com.test.webdriver;




import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.exec.CommandLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;
import com.core.utils.ExcelTestData;
import com.core.utils.ProcessReader;
import com.test.testng.FramworkConfig;
import org.testng.annotations.Test;
import pages.GooglePage;


public class WebDriverTest extends FramworkConfig {
	//static private InputStream fileToRead;
	public static WebDriver driver;
	
	
	@Test(groups = { "regression", "all",  "p1", }, 
			priority = 1,
			enabled = false )
	public void runTest1(){
		//fileToRead= WebDriverTest.class.getClassLoader().getResourceAsStream(globalConfig.getProperty("AllowNetworkAgent.scpt"));
		 

		System.out.println("Found test data as :"+ExcelTestData.getTestData("TestName"));
//		System.out.println("Found test data as :"+ExcelTestData.getTestData("Password"));
//		System.out.println("Found Locators as :"+ExcelTestData.getTestLocators("TEsT2"));
//		System.out.println("Found test data as :"+ExcelTestData.getTestData("UserName"));
		
		
	/*	GooglePage gp = new GooglePage(getDriver());
		gp.work();*/
		
	}
	
	@Test(groups = { "regression", "all",  "p1", }, 
			priority = 1,
			enabled = true )
	public void runTest(){
		
		System.out.println("Found test data as :"+ExcelTestData.getTestData("TestName"));
		System.out.println("Found test data as :"+ExcelTestData.getTestData("Password"));
		System.out.println("Found Locators as :"+ExcelTestData.getTestLocators("TEsT2"));
		System.out.println("Found test data as :"+ExcelTestData.getTestData("UserName"));
	}
	
}
