package com.actiTime.testScripts.placingOrders;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.actiTime.pageLibrary.OrdersPage.OrderManagementPage;
import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TestOrderHistory extends TestBase {
	LoginPage loginPage;
	OrderManagementPage orderPage;

	@BeforeClass
	public void setUpPages()
	{
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		orderPage = PageFactory.initElements(driver, OrderManagementPage.class);
	}

	@Test
	public void testOrderHistory() throws Exception{
		//extentTest = extent.startTest("verify testOrderHistory");
		extentTest.log(LogStatus.INFO, "starting login flow");

		//loginPage = PageFactory.initElements(driver, LoginPage.class);
		//orderPage = PageFactory.initElements(driver, OrderManagementPage.class);

		loginPage.loginToApplication(properties.getUsername(),properties.getPassword());
		extentTest.log(LogStatus.INFO, "checking order history");
		boolean isOrderHistoryDisplayed=orderPage.clickOnOrderHistory();
		Assert.assertTrue(isOrderHistoryDisplayed);

	}

}
