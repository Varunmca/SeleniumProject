package org.vwdautomation.uiAction;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.vwdautomation.testBase.TestBase;
public class HomePage extends TestBase{
	
	
	public static final Logger log = Logger.getLogger(HomePage.class.getName());

	WebDriver driver;
	
	public  HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='username']")
	WebElement loginUserID;
	@FindBy(xpath = "//input[@id='password']")
	WebElement loginPassword;

	@FindBy(xpath = "//a[@id='btn_login']")
	WebElement submitButton;
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logout;
	@FindBy(xpath = ".//*[@id='dialog_body']")
	WebElement authenticationFailed;
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
	
	public void loginToApplication(String userId, String password) throws InterruptedException{
		loginUserID.sendKeys(userId);
		highlightMe( driver,loginUserID);
		log("entered user name:-"+userId+" and object is "+loginUserID.toString());
		loginPassword.sendKeys(password);
		highlightMe( driver,loginPassword);
		log("entered password:-"+password+" and object is "+loginPassword.toString());
		submitButton.click();
		log("clicked on sublit butto and object is:-"+submitButton.toString());
	}
	public String getInvalidLoginText(){
		log("erorr message is:-"+authenticationFailed.getText());
		return authenticationFailed.getText();
	}
	public boolean verifyLogoutDisplay(){
		try {
			logout.isDisplayed();
			log("logout is dispalyed and object is:-"+logout.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
