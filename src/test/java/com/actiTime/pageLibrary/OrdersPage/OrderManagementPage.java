package com.actiTime.pageLibrary.OrdersPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.actiTime.pageLibrary.loginPage.LoginPage;
import com.actiTime.testBase.BasePage;

public class OrderManagementPage extends BasePage {

	public WebDriver driver;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

	public OrderManagementPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//li/a[@title='Dresses' and ./ancestor::div[@id='block_top_menu']]")
	WebElement dressesMenu;

	@FindBy(xpath = "//ul//li/a[@title='Evening Dresses']")
	WebElement eveningDresses;

	@FindBy(xpath = "//div/a[@title='Printed Dress']")
	WebElement printedDress;

	@FindBy(xpath = "//span[contains(.,'Order history and details')]")
	WebElement orderHistory;

	@FindBy(xpath = "//ul[@class='bullet']//a[contains(text(),'My addresses')]")
	WebElement myAddresses;

	@FindBy(xpath = "//span[contains(.,'Update')]")
	WebElement updateAddress;

	@FindBy(id = "company")
	WebElement addressCompany;

	@FindBy(xpath = "//span[contains(.,'Save')]")
	WebElement saveAddress;

	@FindBy(xpath = "//span[contains(.,'Citius Tech Pvt Ltd')]")
	WebElement companyName;

	/*
	 * public void placeOrderForSingleItem() throws Exception {
	 * 
	 * WebElement dressesMenu = getWebElement("orders.dressesMenu");
	 * Reporter.log("clicking dresses menu"); waitUntilVisible(dressesMenu);
	 * dressesMenu.click(); waitABitInSec(3); WebElement eveningDresses =
	 * getWebElement("orders.eveningDresses");
	 * Reporter.log("clicking evening dresses"); eveningDresses.click();
	 * waitABitInSec(3); WebElement printedDress =
	 * getWebElement("orders.printedDress");
	 * Reporter.log("clicking printed dress"); printedDress.click(); }
	 */

	public boolean clickOnOrderHistory() {
		LOGGER.info("clickOnOrderHistory");
		return clickElement(orderHistory);		
	}

	public boolean updateMyAddress() {

		clickOnMyAddresses();
		clickOnUpdateAddress();
		enterCompanyAddress("Citius Tech Pvt Ltd");
		clickOnSaveAddress();
		return isAddressUpdateSuccessful("Citius Tech Pvt Ltd");
			
	}
	
	public boolean clickOnMyAddresses()
	{
		LOGGER.info("clickOnMyAddresses");
		return clickElement(myAddresses);
	}
	
	public boolean clickOnUpdateAddress()
	{
		LOGGER.info("clickOnUpdateAddress");
		return clickElement(updateAddress);
	}
	public boolean enterCompanyAddress(String address)
	{
		LOGGER.info("enterCompanyAddress");
		return setElementText(addressCompany,address);
	}
	public boolean clickOnSaveAddress()
	{
		LOGGER.info("clickOnSaveAddress");
		return clickElement(saveAddress);
	}
	
	public boolean isAddressUpdateSuccessful(String text)
	{
		LOGGER.info("isAddressUpdateSuccessful");
		return isGivenElementAndItsTextDisplayed(companyName,text);
	}
	
	
}
