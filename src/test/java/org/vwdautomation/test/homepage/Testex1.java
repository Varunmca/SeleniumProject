package org.vwdautomation.test.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Testex1 {
	
	@Test 
	public  void test() throws InterruptedException
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://docs.angularjs.org/");
		driver.findElement(By.xpath(".//*[@id='navbar-main']/div/div[1]/ul/li[1]/a")).click();
		Thread.sleep(5000);
		//driver.findElement(By.xpath(".//*[@id='navbar-main']/div/div[1]/ul/li[1]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Tutorial')]")).click();
		Thread.sleep(5000);
	}

}
