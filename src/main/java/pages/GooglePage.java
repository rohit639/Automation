package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.core.utils.ExcelTestData.*;

import java.util.concurrent.TimeUnit;

import com.core.utils.ExcelTestData;
import com.test.testng.FramworkConfig;

public class GooglePage   {
	static WebDriver driver;

	
	public GooglePage(WebDriver driver) {
		this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	public GooglePage work(){
		driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		 
		driver.findElement(getTestLocator("UserId")).sendKeys(getTestData("UserName"));
		//By.id("usernameId")
		driver.findElement(By.className("loginPassword")).sendKeys("");
		
		try {
			Thread.sleep(7000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//driver.findElement(By.className("lsb")).click();
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		driver.findElement(By.id("loginbutton")).click();
		
		try {
			Thread.sleep(10000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this;
	}
	
}
