package TestClass;
import parameters.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import helpers.Base;
import pageobjects.HomePage;

public class FirstTest
{
	public WebDriver driver;
	HomePage hp;
	//SoftAssert softAssertion;
	
	@BeforeClass
	public void setup() {
		Base base = new Base();
		//softAssertion= new SoftAssert();
		driver = base.getWebDriver("CHROME");
		
		hp = new HomePage(driver);
		hp.getHomePage(driver);
		hp.selectLocation();
	}
	
	@Test(dataProvider="ValidParameter",dataProviderClass=DataProvider_Search.class)
	public void ValidSearchItem(String item) throws InterruptedException
	{
		Thread.sleep(5000);
		hp.clearSearch();
		hp.searchEnter(item);
		hp.searchClick();
		Thread.sleep(5000);
		Assert.assertEquals(hp.verifyResult(), true);
		Thread.sleep(5000);
		Assert.assertEquals(hp.verifyAllLinks(), true);//hp.verifyAllLinks();
		Thread.sleep(5000);
		//softAssertion.assertAll();
		//Assert.assertEquals(hp.verifyResult(), true);		
	}
		
	@Test(dataProvider="InvalidParameter",dataProviderClass=DataProvider_Search.class)
	public void InValidSearchItem(String item) throws InterruptedException
	{
		Thread.sleep(5000);
		hp.clearSearch();
		hp.searchEnter(item);
		hp.searchClick();
		Thread.sleep(5000);
		Assert.assertEquals(hp.verifyResult(), false);
		//softAssertion.assertAll();
		//Assert.assertEquals(hp.verifyResult(), false);
	}
	
	@AfterClass
	public void teardown() throws InterruptedException {
		//Thread.sleep(10000);	
		driver.quit();
		//softAssertion.assertAll();		
	}
}