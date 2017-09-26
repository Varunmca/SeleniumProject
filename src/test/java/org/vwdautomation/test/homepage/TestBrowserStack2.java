package org.vwdautomation.test.homepage;
import org.testng.annotations.Test;
import org.vwdautomation.testBase.TestBase;
import org.vwdautomation.uiAction.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

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
public class TestBrowserStack2 extends TestBase{
HomePage homePage;
	
	@BeforeTest
	public void setUp() throws IOException{
     init();
}
	

	@Test
	public void verifyLoginWithValidCredentails() throws InterruptedException{
		log.info("=========== Starting verifyLoginWithValidCredentails Test=============");
		homePage = new HomePage(dr);
		//homepage.switchToFrame();
		homePage.loginToApplication("10021", "passpass");
		System.out.println(homePage.verifyLogoutDisplay());
		Assert.assertTrue(homePage.verifyLogoutDisplay());
		log.info("=========== Finished verifyLoginWithValidCredentails Test=============");
		//homepage.switchToDefaultContent();
    }
}
