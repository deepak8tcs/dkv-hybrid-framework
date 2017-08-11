package com.actiTime.customListner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class Listener extends TestBase implements ITestListener {
	
	private static String imageLink ="<a href=\"%s\"><img src=\"file:///%s\" alt=\"image not found\" height='100' width='100'/></a></br>";
	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
	
	public void onTestStart(ITestResult result) {
		extentTest = extent.startTest( result.getName() +"()");
		LOGGER.info("Test started--->[{}{}]",result.getName(),"()");
		//Reporter.log("---Test started--->"+result.getName(), true);

	}

	public void onTestSuccess(ITestResult result) {
		LOGGER.info("Test passed--->[{}{}]",result.getName(),"()");
		//Reporter.log("---Test passed--->"+result.getName(), true);
		if (result.getStatus() == ITestResult.SUCCESS)
			extentTest.log(LogStatus.PASS, "Test is passed");
		
			extent.endTest(extentTest);

	}

	public void onTestFailure(ITestResult result) {
		LOGGER.info("Test failed--->[{}{}]", result.getName(),"()");
		//Reporter.log("---Test failed--->"+result.getName(), true);
		String imagePath;
		//for emailable reporting
		if (!result.isSuccess()) {
			//	OR	if (result.getStatus() == ITestResult.FAILURE)
			 imagePath = captureScreenshot(driver, result.getName());
			 Reporter.log(String.format(imageLink, imagePath, imagePath));
			
		//for extent reporting
			
			String image = extentTest.addScreenCapture(imagePath);
			extentTest.log(LogStatus.FAIL, image);
			extentTest.log(LogStatus.FAIL, result.getThrowable());
			extentTest.log(LogStatus.FAIL, result.getName() + " is failed");
			//Reporter.setCurrentTestResult(null);

			// below both gives same result: test method name
			// System.out.println("dkv"+result.getMethod().getMethodName());
			// System.out.println("dkv"+result.getName());
		}
		extent.endTest(extentTest);
		
	}

	private String captureScreenshot(WebDriver driver, String imageName) {
		
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp    =new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new Date());
		String dest = System.getProperty("user.dir")+(properties.getPropertyValue("extentReportPath")).replace("/","\\") + timestamp + "_" + imageName + ".png";
		try {
			FileUtils.copyFile(source, new File(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dest;
	}

	public void onTestSkipped(ITestResult result) {
		LOGGER.info("Test skipped--->[{}{}]", result.getName(),"()");
		//Reporter.log("---Test skipped--->"+result.getName(), true);
		if (result.getStatus() == ITestResult.SKIP)
			extentTest.log(LogStatus.SKIP, "Test is skipped");
		
			extent.endTest(extentTest);


	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}