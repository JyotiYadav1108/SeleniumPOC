package com.automation.exercise.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.exercise.TestBase;

public class PaymentPage extends TestBase{

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static final Logger logger = LogManager.getLogger(CheckoutPage.class.getName());
	
	@FindBy(xpath = "//input[@name = 'name_on_card']")
	WebElement nameOnCard;
	
	@FindBy(xpath = "//input[@name = 'card_number']")
	WebElement cardNumber;
	
	@FindBy(xpath = "//input[@name = 'cvc']")
	WebElement cvc;
	
	@FindBy(xpath = "//input[@name = 'expiry_month']")
	WebElement expirationMM;
	
	@FindBy(xpath = "//input[@name = 'expiry_year']")
	WebElement expirationYYYY;
	
	@FindBy(xpath = "//button[text() = 'Pay and Confirm Order']")
	WebElement payAndConfirmOrderButton;
	
	@FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/p")
	WebElement congratulationOrderPlacedText;
	
	@FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/a[@class='btn btn-default check_out']")
	WebElement downloadInvoiceButton;
	
	@FindBy(xpath = "//a[text() = 'Continue']")
	WebElement continueButton;
	
	/*
	 * Method to Enter Payments Details on Payments Page
	 */
	public void enterCardPaymentDetails(String customerNameOnCard, String customerCardNumber, String CVC, String expiryMonth, String expiryYear) {
		nameOnCard.sendKeys(customerNameOnCard);
		cardNumber.sendKeys(customerCardNumber);
		cvc.sendKeys(CVC);
		expirationMM.sendKeys(expiryMonth);
		expirationYYYY.sendKeys(expiryYear);
		
		payAndConfirmOrderButton.click();
	}
	
	/*
	 * Method to Verify 'Order Placed' text
	 */
	public void verifyOrderPlacedText(String orderPlacedText) {
		Assert.assertEquals(congratulationOrderPlacedText.getText(), orderPlacedText);
	}
	
	/*
	 * Method to click on Download Invoice
	 */
	public void clickDownloadInvoice() {
		downloadInvoiceButton.click();
	}
	
	/*
	 * Method to click on Continue Button on Payments Page
	 */
	public void clickContinueButton() {
		continueButton.click();
	}
}
