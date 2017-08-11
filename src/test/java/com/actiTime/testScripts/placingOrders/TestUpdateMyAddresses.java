package com.actiTime.testScripts.placingOrders;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.actiTime.pageLibrary.OrdersPage.OrderManagementPage;
import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TestUpdateMyAddresses extends TestBase {
	LoginPage loginPage;
	OrderManagementPage orderPage;
		
	@BeforeClass
	public void setUpPages()
	{
		 loginPage = PageFactory.initElements(driver, LoginPage.class);
		 orderPage = PageFactory.initElements(driver, OrderManagementPage.class);
	}
	
	@Test
	public void testUpdateAddress() throws Exception{
		//extentTest = extent.startTest("verify testUpdateAddress");
		extentTest.log(LogStatus.INFO, "starting login flow");
		
		loginPage.loginToApplication(properties.getUsername(),properties.getPassword());
		extentTest.log(LogStatus.INFO, "starting updateMyAddress flow");
		boolean isAddressUpdated=orderPage.updateMyAddress();
		Assert.assertTrue(!isAddressUpdated);
	}

}
