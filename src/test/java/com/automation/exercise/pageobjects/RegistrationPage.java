package com.automation.exercise.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.exercise.TestBase;

public class RegistrationPage extends TestBase{
	
	WebDriver driver;
	HomePage homePage = new HomePage(driver);
	LoginPage loginPage = new LoginPage(driver);
	public static final Logger logger = LogManager.getLogger(RegistrationPage.class.getName());
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@data-qa = 'signup-name']")
	WebElement signUpName;
	
	@FindBy(xpath = "//input[@data-qa = 'signup-email']")
	WebElement signUpEmail;
	
	@FindBy(xpath = "//button[@data-qa = 'signup-button']")
	WebElement signUpButton;
	
	@FindBy(xpath = "//input[@id = 'id_gender1']")
	WebElement genderTitleMr;
	
	@FindBy(xpath = "//input[@id = 'id_gender2']")
	WebElement genderTitleMrs;
	
	@FindBy(xpath = "//input[@data-qa = 'password']")
	WebElement signUpPassword;
	
	@FindBy(xpath = "//select[@id = 'days']")
	WebElement dateOfBirthDate;
	
	@FindBy(xpath = "//select[@id = 'months']")
	WebElement dateOfBirthMonth;
	
	@FindBy(xpath = "//select[@id = 'years']")
	WebElement dateOfBirthYear;
	
	@FindBy(xpath = "//input[@id = 'newsletter']")
	WebElement signUpNewsLetter;
	
	@FindBy(xpath = "//input[@id = 'optin']")
	WebElement signUpSpecialOffer;
	
	@FindBy(xpath = "//input[@id = 'first_name']")
	WebElement signUpFirstName;
	
	@FindBy(xpath = "//input[@id = 'last_name']")
	WebElement signUpLastName;
	
	@FindBy(xpath = "//input[@id = 'address1']")
	WebElement address1;
	
	@FindBy(xpath = "//input[@id = 'address2']")
	WebElement address2;
	
	@FindBy(xpath = "//input[@id = 'company']")
	WebElement company;
	
	@FindBy(xpath = "//select[@id = 'country']")
	WebElement country;
	
	@FindBy(xpath = "//input[@id = 'state']")
	WebElement state;
	
	@FindBy(xpath = "//input[@id = 'city']")
	WebElement cityName;
	
	@FindBy(xpath = "//input[@id = 'mobile_number']")
	WebElement mobileNumber;
	
	@FindBy(xpath = "//input[@id = 'zipcode']")
	WebElement zipCode;
	
	@FindBy(xpath = "//button[@data-qa = 'create-account']")
	WebElement createAccountButton;
	
	@FindBy(xpath = "//b")
	WebElement accountCreatedText;
	
	@FindBy(xpath = "//a[@data-qa = 'continue-button']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//a/b")
	WebElement loggedInUserName;
	
	@FindBy(xpath = "//a[@href = '/delete_account']")
	WebElement deleteAccount;
	
	@FindBy(xpath = "//b")
	WebElement accountDeletedText;
	
	@FindBy(xpath = "//button[@type = 'submit']/preceding-sibling::p")
	WebElement emailAlreadyExists;
	
	/*
	 * Method to Enter User Details for User Registration
	 */
	public void registerUser(String name, String email, String title, String password, String days, String month, String year, String firstName, String lastName,  String company, String address1, String address2, String city, String country, String state, String zipCode, String mob, Boolean newsletter , Boolean offers  ) {
		hardWait(5);
		
		signUpName.sendKeys(name);
		signUpEmail.sendKeys(email);
		
		signUpButton.click();
		
		if(title == "Mr.")
			genderTitleMr.click();
		else
			genderTitleMrs.click();
		
		signUpPassword.sendKeys(password);
		
		if(newsletter)
		{
			signUpNewsLetter.click();
		}
		
		if(offers)
		{
			signUpSpecialOffer.click();
		}
		
		signUpFirstName.sendKeys(firstName);
		signUpLastName.sendKeys(lastName);
		
		dateOfBirthDate.click();
		selectDropdownByValue(dateOfBirthDate, days);
		
		dateOfBirthMonth.click();
		selectDropdownByValue(dateOfBirthMonth, month);
		
		dateOfBirthYear.click();
		selectDropdownByValue(dateOfBirthYear, year);
		
		this.company.sendKeys(company);
		this.address1.sendKeys(address1);
		this.address2.sendKeys(address2);
		cityName.sendKeys(city);
		this.zipCode.sendKeys(zipCode);
		this.country.click();
		selectDropdownByValue(this.country, "India");
		this.state.sendKeys(state);
		this.mobileNumber.sendKeys(mob);
		
		createAccountButton.click();
		
		
	}
	
	/*
	 * Method to Enter User Name and Email for User Registration
	 */
	public void registerUser(String name, String email) {
		hardWait(5);
		
		signUpName.sendKeys(name);
		signUpEmail.sendKeys(email);
		
		signUpButton.click();
		
	}
	
	/*
	 * Method to Verify Account Created Text
	 */
	public void verifyAccountCreatedText(String accCreated) {
		String actualText = accountCreatedText.getText();
		Assert.assertEquals(actualText, accCreated, "Verified that Account is Created");
	}

	/*
	 * Method to Click Continue Button
	 */
	public void clickContinueButton() {
		continueBtn.click();
	}
	
	/*
	 * Method to Verify Logged In User Text after Login to confirm login is successful
	 */
	public void verifyLoggedInUser(String userName) {
		String loggedInUserActualText  = loggedInUserName.getText();
		Assert.assertEquals(loggedInUserActualText, userName, "Verified that Logged In As User is Present");
	}
	
	/*
	 * Method to Click on Delete Account
	 */
	public void clickDeleteAccount() {
		deleteAccount.click();
	}
	
	/*
	 * Method to Verify Account Deleted Text
	 */
	public void verifyAccountDeletedText(String accDeleted) {
		String actualDeletedText = accountDeletedText.getText();
		Assert.assertEquals(actualDeletedText, accDeleted, "Verified that Account is Deleted");
	}
	
	/*
	 * Method to Verify Already Exists Text
	 */
	public void verifyEmailAlreadyExistsText() {
		Assert.assertEquals(emailAlreadyExists.getText(),"Email Address already exist!");
	}
	
	

}
