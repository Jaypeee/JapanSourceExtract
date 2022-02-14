package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BrowserClass {
	
	public WebDriver driver;
	
	
	public WebDriver initializeDriver() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		FileHandler.writefile();
		
		return driver;

	}
	
	public void tearDown(){
		driver.quit();
	}

}
