package com.actiTime.testUtils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {

	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static String reportName = "extentReport.html";
	public static PropertyManager properties = PropertyManager.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtentManager.class);
	
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(properties.getPropertyValue("extentReportPath") + reportName);
			extent.loadConfig(new File("./extent-config.xml"));
			extent.addSystemInfo("Environment:", "PRE-PROD");
			extent.addSystemInfo("Browser:", "Chrome");

		}
		LOGGER.debug("ExtentReport instance created");
		//Reporter.log("---ExtentReport instance created---", true);
		return extent;
	}

	public static void closeExtentReporting() {
		extent.flush();
		extent.close();
		LOGGER.debug("ExtentReport instance closed");
		//Reporter.log("---ExtentReport instance closed---", true);
	}
}
