package com.actiTime.testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyManager {

	public File file;
	public FileInputStream fis;
	private static PropertyManager propertyManager = null;
	private static final String propertiesFilePath = "./src/test/resources/properties/";
	private static final String configFileName = "config.properties";
	private static final String inputFileName = "input.properties";

	private Properties prop = new Properties();

	private PropertyManager() {
		loadPropertiesFile(propertiesFilePath, configFileName);
		loadPropertiesFile(propertiesFilePath, inputFileName);
	}
	
	public static PropertyManager getInstance() {
		if(propertyManager ==null)
			propertyManager = new PropertyManager();
		return propertyManager;
	}

	public void loadPropertiesFile(String propertiesFilePath, String configFileName) {

		try {
			file = new File(propertiesFilePath + configFileName);
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getPropertyValue(String key) {
		return prop.getProperty(key);
	}
	
	// these methods are for config.properties file
	public String getBrowserName() {
		return prop.getProperty("browser");
	}

	public int getImplicitTimeout() {
		return Integer.parseInt(prop.getProperty("timeoutInSec.implicitWait"));
	}

	public int getExplicitTimeout() {
		return Integer.parseInt(prop.getProperty("timeoutInSec.explicitWait"));
	}

	public String getChromeDriverPath() {
		return prop.getProperty("webdriver.chrome.driver");
	}

	public String getIEDriverPath() {
		return prop.getProperty("webdriver.ie.driver");
	}

	public String getApplicationUrl() {
		return prop.getProperty("applicationUrl");
	}

	public String getUsername() {
		return prop.getProperty("userName");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

}
