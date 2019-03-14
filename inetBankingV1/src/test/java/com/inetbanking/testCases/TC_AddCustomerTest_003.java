package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

import junit.framework.Assert;

public class TC_AddCustomerTest_003 extends BaseClass{

	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.ClickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details....");

		addcust.custName("Suh");
		addcust.custgender("male");
		addcust.custdob("01", "Nov", "1986");
		Thread.sleep(3000);
		addcust.custaddress("Tolichowki");
		addcust.custcity("Hyderabad");
		addcust.custstate("Telangana");
		addcust.custpinno("500008");
		addcust.custtelephoneno("9989899989");
		
		String email = randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();

		Thread.sleep(3000);
		logger.info("validation started....");

		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if (res==true) {
			Assert.assertTrue(true);
		}
		else {
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);

		}
	}
	
	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public String randomeNum() {
		String generatedstring2 = RandomStringUtils.randomNumeric(4);
		return(generatedstring2);
	}
	
}
