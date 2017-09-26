package org.vwdautomation.test.homepage;
import org.testng.annotations.Test;
import org.vwdautomation.testBase.TestBase;
import org.vwdautomation.uiAction.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
public class TestBrowserStack extends TestBase{
	private WebDriver driver;
	HomePage homePage;
	  //Set up desired capabilities and pass the Android app-activity and app-package to Appium
	 @BeforeClass
	   public void setUp() throws Exception {
	     DesiredCapabilities capability = new DesiredCapabilities();
	     capability.setPlatform(Platform.WINDOWS);
	     capability.setCapability("build", "TestNG - Sample");

	     driver = new RemoteWebDriver(
	       new URL("https://bhoopi1:ZqFUPsm6rQDQXpfCJH1h@hub-cloud.browserstack.com/wd/hub"),
	       capability
	     );
	   }

	   @Test
	   public void testSimple() throws Exception {
	     driver.get("http://www.google.com");
	     System.out.println("Page title is: " + driver.getTitle());
	     WebElement element = driver.findElement(By.name("q"));
	     element.sendKeys("BrowserStack");
	     element.submit();
	     driver = new Augmenter().augment(driver);
	     File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	     try {
	       FileUtils.copyFile(srcFile, new File("Screenshot.png"));
	     } catch (IOException e) {
	       e.printStackTrace();
	     }
	   }
		@Test
		public void verifyLoginWithValidCredentails() throws InterruptedException{
			
			
	    }
	   @AfterClass
	   public void tearDown() throws Exception {
	     driver.quit();
	   }

}
