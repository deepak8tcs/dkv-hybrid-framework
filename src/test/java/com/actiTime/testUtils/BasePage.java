package com.actiTime.testUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.actiTime.excelReader.Xls_Reader;
import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.TestBase;

public class BasePage extends TestBase{

	public Xls_Reader Data;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

	static int explicitWaitInSec = properties.getExplicitTimeout();

	public boolean clickElement(WebElement element) {
		boolean status = true;
		try {
			waitUntilClickable(element);
			element.click();
		} catch (Exception ex) {
			status = false;
			LOGGER.error("error occured while clicking on [{}]: [{}]", element, ex);
		}
		return status;
	}

	public boolean setElementText(WebElement element, String text) {
		boolean status = true;
		try {
			waitUntilVisible(element);
			element.clear();
			element.sendKeys(text);
		} catch (Exception ex) {
			status = false;
			LOGGER.error("error occured while setting text for [{}]: [{}]", element, ex);
		}
		return status;
	}

	public boolean isGivenElementAndItsTextDisplayed(WebElement element, String text) {
		boolean status = false;
		try {
			if (element.isDisplayed())
				status = element.getText().equals(text);
		} catch (Exception ex) {
			LOGGER.error("error occured while verfying the [{}]: [{}]", element, ex);
		}

		return status;
	}

	public boolean selectValueInDropdown(WebElement dropdown, String value) {
		boolean status = true;
		try {
			Select select = new Select(dropdown);
			select.selectByValue(value);
		} catch (Exception ex) {
			status = false;
			LOGGER.error("error occured while selecting value for [{}]: [{}]", dropdown, ex);
		}
		return status;
	}

	public String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception ex) {
			LOGGER.error("error occured while getting page title [{}]", ex);
			return null;
		}

	}

	public void waitABitInSec(int timeToWaitInSec) throws InterruptedException {
		Reporter.log("waiting for " + timeToWaitInSec + " seconds...");
		Thread.sleep(timeToWaitInSec * 1000);
	}

	public void waitUntilVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, explicitWaitInSec);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, explicitWaitInSec);

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilVisible(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilClickable(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void seleteDropDownValue(WebElement element, String dropDownValue) {
		Select select = new Select(element);
		Reporter.log("selecting " + dropDownValue + " value in dropdown");
		select.selectByVisibleText(dropDownValue);
	}

	public String getTimeStamp() {
		return new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new Date());
	}

	public Object[][] getData(String ExcelName, String testcase) {
		Data = new Xls_Reader(System.getProperty("user.dir") + "//src//com//actiTime//testData//" + ExcelName);
		int rowNum = Data.getRowCount(testcase);
		System.out.println(rowNum);
		int colNum = Data.getColumnCount(testcase);
		Object sampleData[][] = new Object[rowNum - 1][colNum];
		for (int i = 2; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				sampleData[i - 2][j] = Data.getCellData(testcase, j, i);
			}
		}
		return sampleData;
	}
}
