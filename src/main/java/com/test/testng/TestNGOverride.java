package com.test.testng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;




public class TestNGOverride   implements  WebDriverEventListener,ITestListener   {
	
	
	static String reportLocation = "./reports";
    static String imageLocation = "images/";
    private static String CONFIGFILE="Framework.config";
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		
		FramworkConfig.log.info("Trying to find the element \""+by+"\"");
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		
		FramworkConfig.log.info("Found the element\""+by+"\"");
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		highLight(element, driver);
		FramworkConfig.log.info("Trying to Click on the element "+element.toString());
	}

	

	public void afterClickOn(WebElement element, WebDriver driver) {
		FramworkConfig.log.info("Clicked on the element"+element.toString());
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		highLight(element, driver);
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		
		
	}

	public void beforeScript(String script, WebDriver driver) {
		
		
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
		
	}

	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
		
	}

	private void highLight(WebElement element, WebDriver driver) {
		for (int i = 0; i < 10; i++) {
			   JavascriptExecutor js = (JavascriptExecutor) driver;
			   js.executeScript(
			     "arguments[0].setAttribute('style', arguments[1]);",
			     element, "color: yellow;border: 20px solid red; ");
			   try {
					Thread.sleep(10l);
				} catch (Exception e) {
					// TODO: handle exception
				}
			   js.executeScript(
						"arguments[0].setAttribute('style', arguments[1]);",
						element, "");
			   
			  }
	}
	
	// This is the method which will be executed in case of test pass or fail
			// This will provide the information on the test
			private void printTestResults(ITestResult result) {
				if (result.getParameters().length != 0) {
					String params = null;
					for (Object parameter : result.getParameters()) {
						params += parameter.toString() + ",";
					}
				}
				String status = null;
				switch (result.getStatus()) {
				case ITestResult.SUCCESS:
					status = "Pass";
					FramworkConfig.log.info("\n");
					FramworkConfig.log.info("=====================================================================================================================================");
					FramworkConfig.log.info("	||  "+result.getTestClass()+" || Method Name  "+result.getName()+" ||  Passed ||");
					FramworkConfig.log.info("=====================================================================================================================================\n");
					break;
				case ITestResult.FAILURE:
					status = "Failed";
					FramworkConfig.log.info("\n");
					FramworkConfig.log.info("=====================================================================================================================================");
					FramworkConfig.log.info("	||  "+result.getTestClass()+" || Method Name  "+result.getName()+" ||  Failed ||");
					FramworkConfig.log.info("=====================================================================================================================================\n");
					break;
				case ITestResult.SKIP:
					status = "Skipped";
					FramworkConfig.log.info("\n");
					FramworkConfig.log.info("=====================================================================================================================================");
					FramworkConfig.log.info("	||  "+result.getTestClass()+" || Method Name  "+result.getName()+" ||  Skipped ||");
					FramworkConfig.log.info("=====================================================================================================================================\n");
				}
			}

			public void afterAlertAccept(WebDriver arg0) {
				// TODO Auto-generated method stub
				
			}

			public void afterAlertDismiss(WebDriver arg0) {
				// TODO Auto-generated method stub
				
			}

			public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
				// TODO Auto-generated method stub
				
			}

			public void beforeAlertAccept(WebDriver arg0) {
				// TODO Auto-generated method stub
				
			}

			public void beforeAlertDismiss(WebDriver arg0) {
				// TODO Auto-generated method stub
				
			}

			public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
				// TODO Auto-generated method stub
				
			}

		
	
	
	
	

}
