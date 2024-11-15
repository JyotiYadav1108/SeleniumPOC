package com.automation.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.LoginPage;
import com.automation.exercise.pageobjects.RegistrationPage;

public class TC005_RegisterUserWithExistingEmail extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	RegistrationPage registrationPage = new RegistrationPage(driver);
	public static final Logger logger = LogManager.getLogger(TC005_RegisterUserWithExistingEmail.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC005_VerifyRegisterUserWithExistingEmail() throws Exception {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		registrationPage = new RegistrationPage(driver);
		String homePageTitle = getData("Home Page Title");
		String userName = getData("UserMainJyoti");
		String userEmail = getData("UserMainEmail");
		String userPassword = getData("UserMainPassword");
		
		logger.info("Verify that home page is visible successfully");
		verifyPageTitle(homePageTitle);
		
		logger.info("Clicking on Signup/Login link from Home Page");
		hardWait(1000);
		homePage.clickSignUpLoginLink();
		
		logger.info("Verify New User Signup Text on Page");
		loginPage.verifyNewUserSignupText();
		
		logger.info("Input already existing email in Email textbox");
		registrationPage.registerUser(userName, userEmail);
		
		logger.info("Verify message that email already existing");
		registrationPage.verifyEmailAlreadyExistsText();
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}
}
