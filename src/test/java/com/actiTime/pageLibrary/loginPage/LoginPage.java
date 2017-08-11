package com.actiTime.pageLibrary.loginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.actiTime.testUtils.BasePage;

public class LoginPage extends BasePage {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "email")
	//@CacheLookup
	WebElement loginUsername;

	@FindBy(css = "#passwd")
	//@CacheLookup
	WebElement loginPassword;

	@FindBy(xpath = "//*[@id='SubmitLogin']/span/i")
	//@CacheLookup
	WebElement loginButton;

	@FindBy(how = How.LINK_TEXT, using = "Sign out")
	//@CacheLookup
	WebElement logoutButton;

	public boolean loginToApplication(String userName, String password) throws Exception {
		
		LOGGER.info("loginToApplication");
		
		enterUsername(userName);
		enterPassword(password);
		clickOnLoginButton();
		return isLoginSuccessful();
	}

	public boolean logoutFromApplication() throws Exception {
		return clickOnLogoutButton();
	}

	public boolean enterUsername(String username) {
		LOGGER.info("enterUsername");
		return setElementText(loginUsername, username);		
	}

	public boolean enterPassword(String password) {
		LOGGER.info("enterPassword");
		return setElementText(loginPassword, password);
	}

	public boolean clickOnLoginButton() {
		LOGGER.info("clickOnLoginButton");
		return clickElement(loginButton);		
	}

	public boolean clickOnLogoutButton() {
		LOGGER.info("clickOnLogoutButton");
		return clickElement(logoutButton);
	}

	public boolean isLoginSuccessful() {
		LOGGER.info("isLoginSuccessful");
		return getPageTitle().contains("My account");

	}

}
