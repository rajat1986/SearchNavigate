package stepimplementation;

import static org.testng.AssertJUnit.assertEquals;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Base;
import pageobjects.HomePage;

public class SearchNavigate {	
	public WebDriver driver;
	public HomePage hp;
	public SoftAssert softAssertion;
	   
    @Before
    public void openBrowser() throws MalformedURLException {
    	Base base = new Base();
    	driver = base.getWebDriver("CHROME");
    }

     
    @After
    public void teardown(/*Scenario scenario*/) {
        driver.quit();
        
    }

	@When("^I am on HomePage$")
	public void i_am_on_Home_Page() throws Throwable {
		hp = new HomePage(driver);
		hp.getHomePage(driver);
		hp.selectLocation();		
	}
	
	@When("^I searched for \"([^\"]*)\"$")
	public void i_searched_for_item(String item) throws Throwable {
			hp.clearSearch();
			hp.searchEnter(item);
			hp.searchClick();
			}
	
	@Then("Search was successful")
	public void search_successful() throws Throwable {
		Assert.assertEquals(hp.verifyResult(), true);
			}
	
	@Then("Search failed")
	public void search_failed() throws Throwable {
		Assert.assertEquals(hp.verifyResult(), false);
		}
	
	@Then("All links were found working")
	public void all_links_were_found_working() throws Throwable {
		Assert.assertEquals(hp.verifyAllLinks(), true);
		}
}
