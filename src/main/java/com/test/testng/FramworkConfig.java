package com.test.testng;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.core.utils.ExcelTestData;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.MarionetteDriverManager;






@Listeners(TestNGOverride.class)
public class FramworkConfig extends TestNG  {
	public static WebDriver driver;
	public static Properties globalConfig=new Properties();
	public static String CONFIGFILE="Framework.config";
	
	public static  Logger log = Logger.getLogger(FramworkConfig.class);
	
	
	
	public FramworkConfig(){
		try {
			globalConfig.load(getClass().getClassLoader().getResourceAsStream(CONFIGFILE));
			System.out.println("This will print correct");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	


	

	@BeforeSuite
	public static void settingEnvironment(){
		ExcelTestData.executeExcelFile();
		String currentDir = System.getProperty("user.dir");
		String fullPath = currentDir+"/src/main/resoucres/wires.exe";
		System.setProperty("webdriver.gecko.driver", fullPath);
	}
	
	@BeforeMethod
	public static void methodName(Method method) {
		//Call this method to generate the html file
		if(method!=null){
		ExcelTestData.setMethodName(method.getName());
		}
		}
	
	@BeforeTest
	public static void startDriver() throws Exception {
		//Call this method to generate the html file
		
		
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		if (globalConfig.getProperty("Browser").equalsIgnoreCase("SAFARI")) {
			desiredCapabilities.setBrowserName(DesiredCapabilities
					.safari().getBrowserName());
			desiredCapabilities.setPlatform(Platform.MAC);
			desiredCapabilities.setJavascriptEnabled(true);
			driver = new SafariDriver(desiredCapabilities);

		} 
		
		else if (globalConfig.getProperty("Browser").toString().equalsIgnoreCase("firefox")) {
			
			System.out.println("This is printing");
			desiredCapabilities.setBrowserName(DesiredCapabilities
					.firefox().getBrowserName()); 
			driver = new FirefoxDriver(desiredCapabilities);
//			String currentDir = System.getProperty("user.dir");
//			String fullPath = currentDir+"/src/main/resoucres/wires.exe";
//			System.setProperty("webdriver.gecko.driver", fullPath);
			//MarionetteDriverManager.getInstance().setup();
			//desiredCapabilities.setCapability("marionette", true);
//			driver = new FirefoxDriver(desiredCapabilities);
			//driver = new MarionetteDriver();
//			Runtime runtime = Runtime.getRuntime();
//			String[] args = { "osascript", "-e", "tell app \"firefox\" to activate" };
//			Process process = runtime.exec(args);
			/*//MarionetteDriverManager.getInstance().setup();
		
			//capabilities.setCapability("marionette", true);
			// driver = new MarionetteDriver(capabilities);
//			String firefox = "src/main/resoucres/wires";
//			System.setProperty("webdriver.firefox.driver", firefox);
			desiredCapabilities.setBrowserName(DesiredCapabilities
					.firefox().getBrowserName());
			 driver = new FirefoxDriver(desiredCapabilities);
		
*/
		} 
		
		else if (globalConfig.getProperty("Browser").equalsIgnoreCase("chrome")) {
			String chromedriver = "src/main/resoucres/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromedriver);
			driver = new ChromeDriver();
		} 
		
		else if (globalConfig.getProperty("Browser").equalsIgnoreCase("IE")||globalConfig.getProperty("Browser").equalsIgnoreCase("internetExplorer")) {
			driver = new InternetExplorerDriver();
		} 

		else if (globalConfig.getProperty("Browser").equalsIgnoreCase("html")) {

			/*logger.info("initializing 'html' driver...");
			driver = new HtmlUnitDriver();*/

		}
		long time =Long.parseLong(globalConfig.getProperty("TimeoutSeconds"));
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		//driver=register(driver);
	//	driver.manage().timeouts().pageLoadTimeout(GlobalConstants.TIMEOUTSECONDS, TimeUnit.SECONDS);

		

		log.info("Browser Opened");	

	}
	
	
	
	@AfterTest(alwaysRun = true)
	public void stopDriver() throws Exception {
		log.info("Closing Browser \n");
		if(driver!=null)
		driver.quit();
	
	}
}
