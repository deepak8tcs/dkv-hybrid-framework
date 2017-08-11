package com.actiTime.testBase;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.actiTime.testUtils.Driver;
import com.actiTime.testUtils.ExtentManager;
import com.actiTime.testUtils.PropertyManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {

	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static PropertyManager properties =PropertyManager.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);
	
	@BeforeSuite()
	public void setUpReporting() {	
		LOGGER.debug("setUpReporting--->");
		extent = ExtentManager.getInstance();
	}

	@BeforeClass()
	public void setUpDriver() throws IOException {	
		LOGGER.debug("setUpDriver--->");
		driver = Driver.initialize();
		
	}
	
	
	@AfterClass()
	public void tearDownDriver() {
		LOGGER.debug("tearDownDriver--->");
		Driver.closeBrowser();
	}

	@AfterSuite()
	public void closeReporting() {
		LOGGER.debug("closeReporting--->");
		ExtentManager.closeExtentReporting();

	}

}
