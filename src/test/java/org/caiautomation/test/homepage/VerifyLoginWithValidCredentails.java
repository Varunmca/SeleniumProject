package org.caiautomation.test.homepage;

import java.io.IOException;

import org.caiautomation.testBase.TestBase;
import org.caiautomation.uiAction.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyLoginWithValidCredentails extends TestBase{

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