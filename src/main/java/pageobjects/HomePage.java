package pageobjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.Base;

public class HomePage {
	@FindBy(xpath = "//div[@class='search__box']/*[@class='react-autosuggest__input']")
	WebElement SearchText;
	// div[@class='search__box']/*[@class='react-autosuggest__input']")
	@FindBy(xpath = "//div[@class='search__box']/*[@class='btn search__btn']")
	WebElement SearchButton;
	@FindBy(xpath = "//IMG[@class='img animated fadeIn cities-container-list__item-img'][1]")
	WebElement Location;
	@FindBy(xpath = "//img[@class='no-result__img']")
	WebElement NoResult;

	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void selectLocation() {
		try {
			System.out.println("Location" + Location);
			Location.click();
			// Location.sendKeys("New Delhi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearSearch() {
		try {
			SearchText.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchEnter(String strSearch) {
		try {
			SearchText.clear();
			SearchText.sendKeys(strSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchClick() {
		try {
			SearchText.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyResult() {
		try {
			if (Base.waitForElementVisible(driver, NoResult)) {
				if (NoResult.isDisplayed())
					return false;
				else
					return true;
			} else
				return true;
		} catch (Exception e) {
			return true;
		}
	}

	public void getHomePage(WebDriver driver) {
		driver.get("https://grofers.com");
		// PageFactory.initElements(driver, HomePage.class);
	}

	public boolean verifyAllLinks() throws InterruptedException {
		//PageFactory.initElements(driver, HomePage.class);
		List<WebElement> links = driver.findElements(By.xpath("//a[@class='product__wrapper']"));

		String url ="";
		int linkcount = links.size();
		int validlinkcount = 0;
		
		System.out.println("No. of links :"+linkcount);
		for (WebElement link : links)
		{
			url = link.getAttribute("href");		

			System.out.println("URL1:"+url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				return false;
				//continue;
			}
			
			HttpURLConnection huc = null;
			int respCode = 400;
			
			try {
				if (Base.waitForElementClickable(driver, link)) 
				{
					huc = (HttpURLConnection) (new URL(url).openConnection());
					
					huc.setRequestMethod("HEAD");
	
					huc.connect();
					Thread.sleep(5000);
					respCode = huc.getResponseCode();
	
					if (respCode != 200) {
						//System.out.println(url + " is a valid link");
						System.out.println(url + " is a broken link with response code "+respCode);
						return false;
					} 
					else
						System.out.println(url + " is a valid link with response code "+respCode);
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}