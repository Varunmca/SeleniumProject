package org.caiautomation.test.homepage;

import java.io.IOException;

import org.caiautomation.testBase.TestBase;
import org.caiautomation.uiAction.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyLoginWithInvalidCredentails extends TestBase{

	HomePage homePage;
	
	@BeforeTest
	public void setUp() throws IOException{
     init();
}
	

	@Test
	public void verifyLoginWithValidCredentails() throws InterruptedException{
		log.info("=========== Starting verifyLoginWithInValidCredentails Test=============");
		homePage = new HomePage(dr);
		//homepage.switchToFrame();
		homePage.loginToApplication("10021", "passpass11");
		System.out.println(homePage.getInvalidLoginText());
		Assert.assertEquals(homePage.getInvalidLoginText(), "The UserID or password you entered is incorrect.");
		log.info("=========== Finished verifyLoginWithInvalidCredentails Test=============");
		//homepage.switchToDefaultContent();
    }
}