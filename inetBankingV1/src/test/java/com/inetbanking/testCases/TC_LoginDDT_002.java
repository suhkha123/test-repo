package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.ClickSubmit();
	
		if (isAlertPresent()==true) {
			
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed - Check User ID or Password");
			
		}
		else
		{
			Assert.assertTrue(true);
			lp.ClickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Login Passed");

		}
		
		
	}
	
	
	public boolean isAlertPresent() {
		try {
		
			driver.switchTo().alert();
			return true;
		
		}	
		catch(NoAlertPresentException e) {
			
			return false;			
		}
	}
	
	
	
	
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		String path =System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum= XLUtils.getRowCount(path, "Sheet1");
		int cocount= XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]= new String [rownum][cocount];
		for(int i=1;i<rownum;i++) {
			for(int j=0;j<cocount;j++) {
				logindata[i-1][j]= XLUtils.getCellData(path,"Sheet1",i,j);
			}
		}
		
		return logindata;
	}
	
}
