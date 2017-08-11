package com.actiTime.testUtils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
	
	public static WebDriver driver=null;
	public static PropertyManager properties =PropertyManager.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(Driver.class);

	public  static WebDriver initialize() {
		String browser = properties.getBrowserName().toLowerCase();
		if(driver==null)
		{
			if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", properties.getPropertyValue("webdriver.gecko.driver"));
				driver = new FirefoxDriver();

			} else if (browser.equals("chrome")) {
				LOGGER.info("Launching chrome browser...");
				System.setProperty("webdriver.chrome.driver", properties.getChromeDriverPath());
				driver = new ChromeDriver();

			} else if (browser.equals("ie")) {
				System.setProperty("webdriver.ie.driver", properties.getIEDriverPath());
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(properties.getImplicitTimeout(), TimeUnit.SECONDS);	
			loadApplication();

		}
		return driver;
	}
	
	public static void loadApplication()
	{
		LOGGER.debug("Loading application url...");
		driver.get(properties.getApplicationUrl());
	}
	

	public static void closeBrowser() {
		LOGGER.debug("Closing chrome browser...");
		driver.close();
		driver=null;

	}
	public void quitBrowser() {
		driver.quit();
		driver=null;
	}
	
}
