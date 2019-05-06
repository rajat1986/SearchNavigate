
package TestClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_Links {
    
    private static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        
        String homePage = "https://grofers.com/";
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        
		System.setProperty( "webdriver.chrome.driver",
				"C:/RJK2/Learning/Selenium/chromedriver.exe" );
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();        
        
        driver.get(homePage);
        WebElement Location = driver.findElement(By.xpath("//IMG[@class='img animated fadeIn cities-container-list__item-img'][1]"));
        WebElement Searchtext = driver.findElement(By.xpath("//div[@class='search__box']/*[@class='react-autosuggest__input']"));
        Location.click();
        Searchtext.sendKeys("Sugar");
        Searchtext.sendKeys(Keys.ENTER);
        Thread.sleep(20000);
        
        List<WebElement> links = driver.findElements(By.xpath("//a[@class='product__wrapper']"));
        
        Iterator<WebElement> it = links.iterator();
        System.out.println("Count : "+links.size());
        
        while(it.hasNext()){
            
            url = it.next().getAttribute("href");
            
            System.out.println(url);
        
            if(url == null || url.isEmpty()){
System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            if(!url.startsWith(homePage)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println(url+" is a broken link with code "+respCode);
                }
                else{
                    System.out.println(url+" is a valid link with code "+respCode);
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        driver.quit();

    }
}