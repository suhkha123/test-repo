package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;


import org.apache.log4j.*;
public class BaseClass {

	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUP(String br) {
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if (br.equals("chrome")) {
		
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
		
		}else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			ProfilesIni profile = new ProfilesIni();
	        FirefoxProfile testprofile = profile.getProfile("Suh_selenium");
	        DesiredCapabilities dc = DesiredCapabilities.firefox();
	        dc.setCapability(FirefoxDriver.PROFILE, testprofile);
	        FirefoxOptions opt = new FirefoxOptions();
	        opt.merge(dc);
	        driver =  new FirefoxDriver(dc);
			
		}else if(br.equals("ie")) {
		
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		
		} 
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}
