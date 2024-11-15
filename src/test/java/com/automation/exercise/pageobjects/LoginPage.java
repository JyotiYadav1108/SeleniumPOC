package com.automation.exercise.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.exercise.TestBase;

public class LoginPage extends TestBase {
	
	WebDriver driver;
	HomePage homePage;
	public static final Logger logger = LogManager.getLogger(LoginPage.class.getName());
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@data-qa = 'login-password']")
	WebElement loginPassword;
	
	@FindBy(xpath = "//input[@data-qa = 'login-email']")
	WebElement loginEmail;
	
	@FindBy(xpath = "//button[@data-qa = 'login-button']")
	WebElement loginButton;
	
	@FindBy(xpath = "//div[@class = 'login-form']/h2")
	WebElement loginToAccountText;
	
	@FindBy(xpath = "//div[@class = 'signup-form']/h2")
	WebElement newUserSignup;
	
	@FindBy(xpath = "(//ul[@class = 'nav navbar-nav']/li)[10]/a")
	WebElement loggedInAsText;
	
	@FindBy(xpath = "//button[@type = 'submit']/preceding-sibling::p")
	WebElement incorrectEmailPasswordMessageText;
	
	/*
	 * Method to Login to Automation Exercise Website
	 */
	public void loginToAutomationExercise(String username, String password) {
		logger.info("Enter Login Email in Input Box");
		loginEmail.sendKeys(username);
		logger.info("Enter Password in Input Box");
		loginPassword.sendKeys(password);
		logger.info("Click on Login Button");
		loginButton.click();
	}

	/*
	 * Method to Verify 'Login to your account' text
	 */
	public void verifyLoginToAccountText() {
		Assert.assertEquals("Login to your account", loginToAccountText.getText());
	}
	
	/*
	 * Method to Verify 'New User Signup!' text
	 */
	public void verifyNewUserSignupText() {
		Assert.assertEquals("New User Signup!", newUserSignup.getText());
	}
	
	/*
	 * Method to Verify 'Logged in as' Text after user has logged in
	 */
	public void verifyLoggedInAsUserText(String user) {
		Assert.assertEquals("Logged in as " + user, loggedInAsText.getText());
	}
	
	/*
	 * Method to Verify message for incorrect details
	 */
	public void verifyIncorrectEmailPasswordMessageText(String message) {
		logger.info("Verify message for incorrect details");
		Assert.assertEquals(message, incorrectEmailPasswordMessageText.getText());
	}

	
	
}

