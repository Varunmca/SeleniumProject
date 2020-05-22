package org.caiautomation.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver dr;
	
	public Properties OR =  new Properties();
	public static ExtentReports extent;
	 public static ExtentTest test;
	 public ITestResult result;
	 
	 static{
    	 Calendar calendar = Calendar.getInstance();
  		 SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
  		 extent = new ExtentReports(System.getProperty("user.dir") +"/src/main/java/org/caiautomation/report/test"+formater.format(calendar.getTime())+".html", true);
     }
	
	public void loadData() throws IOException{
   	 File file = new  File(System.getProperty("user.dir")+"/src/main/java/org/vwdautomation/config/config.properties");
   	 FileInputStream f = new FileInputStream(file);
   	 OR.load(f);
   			 
    }
	
	
	
	public void init() throws IOException{
  	  loadData();
  	  String log4jConfPath = "log4j.properties";
  	  PropertyConfigurator.configure(log4jConfPath);
  	  System.out.println(OR.getProperty("browser"));
  	  selectBrowser(OR.getProperty("browser"));
  	  getUrl(OR.getProperty("url"));
   }
	
	
	public static void highlightMe(WebDriver driver, WebElement element) throws InterruptedException {
		// Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute javascript
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
		Thread.sleep(3000);
		js.executeScript("arguments[0].style.border=''", element);
	}
public void selectBrowser(String browser) throws MalformedURLException{
		
		if(browser.equalsIgnoreCase("firefox")){
			//https://github.com/mozilla/geckodriver/releasess
			// For Mac os
			//System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
			//log.info("creating object of "+browser);
			//dr = new FirefoxDriver();
			//driver = new EventFiringWebDriver(dr);
			//eventListener = new WebEventListener();
			//driver.register(eventListener);
			//setDriver(driver);
			
			//For Window
			System.setProperty("webdriver.gecko.driver ", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			log.info("creating object of "+browser);
			dr = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			//https://sites.google.com/a/chromium.org/chromedriver/downloads
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			// For Windows system
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			log.info("creating object of "+browser);
			dr = new ChromeDriver();
			//log.info("creating object of "+browser);
			//dr = new ChromeDriver();
			//driver = new EventFiringWebDriver(dr);
			//eventListener = new WebEventListener();
			//driver.register(eventListener);
		}
		else if(browser.equalsIgnoreCase("browserStack")){
			DesiredCapabilities capability = new DesiredCapabilities();
		     capability.setPlatform(Platform.WINDOWS);
		     capability.setCapability("build", "TestNG - Sample");
             System.out.println("Brosersatack server stared");
		     dr = new RemoteWebDriver(
		       new URL("https://bhoopi1:ZqFUPsm6rQDQXpfCJH1h@hub-cloud.browserstack.com/wd/hub"),
		       capability
		     );
		}
	}


public void getUrl(String url){
	 log.info("navigating to :-"+url);
	 dr.get(url);
	 dr.manage().window().maximize();
	 dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element){
	 WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	 wait.until(ExpectedConditions.visibilityOf(element));
}


@AfterClass(alwaysRun=true)
public void endTest() {
	closeBrowser();
}

public void closeBrowser(){
	dr.quit();
	log.info("browser closed");
	extent.endTest(test);
	extent.flush();
}

public void getresult(ITestResult result){
	if(result.getStatus()==ITestResult.SUCCESS){
		test.log(LogStatus.PASS,result.getName()+" test is pass");
	}
	else if(result.getStatus()==ITestResult.SKIP){
		test.log(LogStatus.SKIP,result.getName()+" test is skipped and skip reason is:-"+result.getThrowable());
	}
	else if(result.getStatus()==ITestResult.FAILURE){
		test.log(LogStatus.ERROR,result.getName()+" test is failed"+ result.getThrowable());
		String screen = captureScreen("");
		test.log(LogStatus.FAIL,test.addScreenCapture(screen));
	}
	else if(result.getStatus()==ITestResult.STARTED){
		test.log(LogStatus.INFO,result.getName()+" test is started");
	}
}
public String captureScreen(String fileName) {
	if(fileName==""){
		fileName="blank";
	}
	File destFile = null;
	Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
			File scrFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
			
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/org/vwdautomation/screenshot/";
				destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(scrFile, destFile);
				// This will help us to link the screen shot in testNG report
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destFile.toString();
}
public WebElement waitForElement( WebDriver driver,WebElement element ,long timeOutInSeconds){
	WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	wait.until(ExpectedConditions.elementToBeClickable(element));
	return element;
}

@AfterMethod()
public void afterMethod(ITestResult result){
	getresult(result);
}

@BeforeMethod()
public void beforeMethod(Method result){
	test = extent.startTest(result.getName());
	test.log(LogStatus.INFO, result.getName()+" test Started");
}

}
