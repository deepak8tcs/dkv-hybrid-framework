package com.actiTime.testScripts.login;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TestLoginwithInputFromPropertiesFile extends TestBase{
	
	LoginPage loginPage;
	
	@BeforeClass
	public void setUpPages()
	{
		 loginPage = PageFactory.initElements(driver, LoginPage.class);
	}
		
	@Test
	public void testLogin() throws Exception{
		
		//extentTest = extent.startTest("verify login");
		extentTest.log(LogStatus.INFO, "starting login flow");
		//loginPage = PageFactory.initElements(driver, LoginPage.class);
		boolean isLoginSuccessful=loginPage.loginToApplication(properties.getPropertyValue("userName"),properties.getPropertyValue("password"));
		Assert.assertTrue(isLoginSuccessful);
	}
	
	@Test
	public void testLogout() throws Exception{
		//extentTest = extent.startTest("verify logout");
		extentTest.log(LogStatus.INFO, "starting logout flow");
		boolean isLogoutSuccessful=loginPage.logoutFromApplication();
		Assert.assertTrue(!isLogoutSuccessful);		
	}
	
}
