package com.actiTime.testScripts.login;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.TestBase;
import com.actiTime.testUtils.ExcelManager;
import com.relevantcodes.extentreports.LogStatus;

public class TestLoginWithParameterization extends TestBase{
	

	LoginPage loginPage;
	public static ExcelManager excelManager =  ExcelManager.getInstance();
	
	@BeforeClass
	public void setUpPages()
	{
		 loginPage = PageFactory.initElements(driver, LoginPage.class);
	}
	
	@DataProvider
	public  Object[][] getData() throws IOException {
//		for(int i=0;i<5;i++)
//		System.out.println(excelManager.getExcelData(1, 1, i));
	
		//excelManager.printExcelDataWhenNoOfCellsNotSameInAllRows(3);
		//excelManager.getListOfExcelData(1);
		excelManager.getEmployeeRecord(4);
		return excelManager.getArrayOfObject(0);
	}


	@Test(dataProvider = "getData")
	public void testLoginWithParameterization(String TestName, String userName, String password,String runMode) throws Exception {
		if(runMode.equals("N")) {
			throw new SkipException("Skipped Test case is");
		}
		
		extentTest.log(LogStatus.INFO, "starting login flow. User: "+userName+"  Password: "+password);
		//loginPage = PageFactory.initElements(driver, LoginPage.class);
		boolean isLoginSuccessful=loginPage.loginToApplication(userName,password);
		Assert.assertTrue(isLoginSuccessful);
		loginPage.logoutFromApplication();
	}
		
}
