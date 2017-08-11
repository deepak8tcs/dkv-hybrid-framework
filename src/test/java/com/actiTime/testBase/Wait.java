package com.actiTime.testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Function;

public class Wait extends TestBase {
	
	int explicitWaitInSec;
	
	public void setImplicitWait(int timeInsec) {
		Reporter.log("waiting for page to load...");
		try {
			driver.manage().timeouts().implicitlyWait(timeInsec, TimeUnit.SECONDS);
			Reporter.log("Page is loaded");
		} catch (Throwable error) {
			Reporter.log("Timeout for Page Load Request to complete after " + timeInsec + " seconds");
			Assert.assertTrue(false, "Timeout for page load request after " + timeInsec + " second");
		}
	}

	public void setExplicitWait(int timeInsec) {
		explicitWaitInSec = timeInsec;

	}


	public void waitABitInSec(int timeToWaitInSec) throws InterruptedException {
		Reporter.log("waiting for " + timeToWaitInSec + " seconds...");
		Thread.sleep(timeToWaitInSec * 1000);
	}

	public void waitUntilVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, explicitWaitInSec);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilVisible(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilClickable(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
