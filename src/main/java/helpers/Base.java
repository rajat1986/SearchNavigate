package helpers;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base
{
	public WebDriver getWebDriver(String strBrowserType) {
		switch (strBrowserType.toUpperCase()) 
		{
			case "FIREFOX":
				System.out.println(strBrowserType);			
				return new FirefoxDriver();

		case "IE":
			String path=System.getProperty("user.dir");
			File file = new File(path+"//datafile//IEDriverServer.exe");
	    	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	    	System.out.println("Started Test case1");
	    	
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.
			   INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			//WebDriver driver = new InternetExplorerDriver(capabilities);
	    	return new InternetExplorerDriver(capabilities);
	    	
	    	
	    	  

		case "CHROME":
			
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("--user-data-dir=C:/Default" + Globals.BROWSERCERTDIR);
			//options.addArguments("--user-data-dir=" + Globals.BROWSERCERTDIR + "--ignore-certificate-errors");
			//return new ChromeDriver(options);
	    	System.out.println("Called openBrowser");
			System.setProperty( "webdriver.chrome.driver",
					"C:/RJK2/Learning/Selenium/chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
	    	driver.manage().deleteAllCookies();
	    	driver.manage().window().maximize();
	    	return driver;
	    	

		default:
			throw new RuntimeException("Browser is not supported");

		}

	}
	
	
	public static boolean waitForElementClickable(WebDriver driver, WebElement webelement) {
		try {
			int timeoutInSeconds = 20;
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(webelement));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator)(webelement));
			wait.until(ExpectedConditions.elementToBeClickable(webelement));
			//System.out.println(webelement + " Waiting for "+timeoutInSeconds+ " seconds.");
			return true;
		} catch (TimeoutException e) {
			System.out.println(webelement + " Webelement not clickable" + e);
			return false;

		}
	}
	
	public static boolean waitForElementVisible(WebDriver driver, WebElement webelement) {
		try {
			int timeoutInSeconds = 20;
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(webelement));			
			//System.out.println(webelement + " Waiting for "+timeoutInSeconds+ " seconds.");
			return true;
		} catch (TimeoutException e) {
			System.out.println(webelement + " Webelement not visible" + e);
			return false;

		}
	}

}