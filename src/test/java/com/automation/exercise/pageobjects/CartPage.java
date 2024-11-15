package com.automation.exercise.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.exercise.TestBase;

public class CartPage extends TestBase{
	
	WebDriver driver;
	
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static final Logger logger = LogManager.getLogger(CartPage.class.getName());
	
	@FindBy(xpath = "//div[@class = 'single-widget']/h2")
	WebElement subscriptionText;
	
	@FindBy(xpath = "//input[@id = 'susbscribe_email']")
	WebElement subscriptionEmail;
	
	@FindBy(xpath = "//button[@id = 'subscribe']")
	WebElement subscriptionSubmit;
	
	@FindBy(xpath = "//div[@class = 'alert-success alert']")
	WebElement subscriptionSuccessful;
	
	@FindBy(xpath = "//a[contains(@class, 'check_out')]")
	WebElement proceedToCheckOutButton;
	
	@FindBy(xpath = "//div[@class = 'modal-body']//a")
	WebElement registerOnCheckoutLink;
	
	@FindBy(xpath = "//span[@id='empty_cart']/p")
	WebElement emptyCartText;
	
	@FindBy(xpath = "//a/u[text() = 'Register / Login']")
	WebElement registerLoginDialog;
	
	@FindBy(id = "cart_info_table")
	WebElement cartItems;
	
	/*
	 * Method to Verify for Subscription in Carts 
	 */
	public void verifySubsciptionTextOnCartsPage() {
		scrollDownToSpecificElement(subscriptionText);
		Assert.assertEquals(subscriptionText.getText(), "SUBSCRIPTION");
	}
	
	/*
	 * Method to Enter email in Subscription Box
	 */
	public void fillDetailsInSubscriptionBox(String email) {
		subscriptionEmail.sendKeys(email);
		subscriptionSubmit.click();
	}
	
	/*
	 * Method to Verify successful message after subscribing on Carts Page
	 */
	public void verifySuccessMessageAfterSubscribing() {
		Assert.assertEquals(subscriptionSuccessful.getText(), "You have been successfully subscribed!");
	}
	
	/*
	 * Method to click on Proceed to Checkout Button
	 */
	public void clickProceedToCheckOutButton() {
		proceedToCheckOutButton.click();
	}
	
	/*
	 * Method to verify Empty Cart Text
	 */
	public void verifyEmptyCartDisplayText() {
		Assert.assertEquals(emptyCartText.getText(), "Cart is empty! Click here to buy products.");
	}
	
	/*
	 * Method to Click 'Register/Login' link 
	 */
	public void clickRegisterLoginLink() {
		registerLoginDialog.click();
	}
	
	/*
	 * Method to verify Items in Cart
	 */
	public void verifyItemsInCartArePresent() {
		Assert.assertTrue(cartItems.isDisplayed());
	}
}
