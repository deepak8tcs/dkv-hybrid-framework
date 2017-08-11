/*package com.actiTime.testScripts.placingOrders;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.actiTime.pageLibrary.OrdersPage.OrderManagementPage;
import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.BaseClass;

public class TestPlaceAnOrder extends BaseClass {
LoginPage loginPage = new LoginPage();
OrderManagementPage orderPage = new OrderManagementPage();
	
	@BeforeClass
	public void setUp() throws IOException{
		init();
	}
	
	@Test
	public void placeAnOrder() throws Exception{
		loginPage.loginToApplication();
		orderPage.placeOrderForSingleItem();
		Assert.assertTrue(true);
	
	}
	
	@Test
	public void test2() throws Exception{
	
		Assert.assertTrue(true);
		
	}
	
	@AfterClass
	
	public void testDown(){
		closeBrowser();
	}

}
*/