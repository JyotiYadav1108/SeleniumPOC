package com.automation.exercise.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class ContactUsPage {
	
	WebDriver driver;
	public static final Logger logger = LogManager.getLogger(ContactUsPage.class.getName());
	
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'contact-form']/h2")
	WebElement getInTouchText;
	
	@FindBy(xpath = "//input[@data-qa = 'name']")
	WebElement contactUsName;
	
	@FindBy(xpath = "//input[@data-qa = 'email']")
	WebElement contactUsEmail;
	
	@FindBy(xpath = "//input[@data-qa = 'subject']")
	WebElement contactUsSubject;
	
	@FindBy(xpath = "//textarea[@data-qa = 'message']")
	WebElement contactUsMessage;
	
	@FindBy(xpath = "//input[@type='file']")
	WebElement uploadFile;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement submitButton;
	
	@FindBy(xpath = "//div[@class = 'status alert alert-success']")
	WebElement successMessage;
	
	@FindBy(xpath = "")
	WebElement okButton;
	
	@FindBy(xpath = "//div[@id = 'form-section']/a")
	WebElement homeBackButton;

	/*
	 * Method to verify 'Get In Touch' text on Contact Page
	 */
	public void verifyGetInTouchText() {
		Assert.assertEquals(getInTouchText.getText(), "GET IN TOUCH");
	}
	
	/*
	 * Method to enter Contact Us Form Details 
	 */
	public void contactUsFormFillDetails(String name, String email, String subject, String message, String fileLocation) {
		contactUsName.sendKeys(name);
		contactUsEmail.sendKeys(email);
		contactUsSubject.sendKeys(subject);
		contactUsMessage.sendKeys(message);
		
		uploadFile.sendKeys(fileLocation);
		submitButton.click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();	
	}
	
	/*
	 * Method to verify success message on Contact Us Form Page
	 */
	public void verifySuccessMessage() {
		Assert.assertEquals(successMessage.getText(), "Success! Your details have been submitted successfully.");
	}
	
	/*
	 * Method to Click Home after Contact Form is submitted
	 */
	public void clickBackHomeButton() {
		homeBackButton.click();
	}
}
