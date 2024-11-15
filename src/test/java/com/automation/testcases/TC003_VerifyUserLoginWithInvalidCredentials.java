package com.automation.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

 
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.LoginPage;

public class TC003_VerifyUserLoginWithInvalidCredentials extends TestBase{
	
	HomePage homePage = new HomePage(driver);
	LoginPage loginPage = new LoginPage(driver);
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC003_VerifyInvalidUserLogin() throws Exception {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		String homePageTitle = getData("Home Page Title");
		String userEmail = getData("IncorrectUserEmail");
		String userPassword = getData("IncorrectUserPassword");
		String incorrectUserError = getData("IncorrectUserError");
		
		logger.info("Verify that home page is visible successfully");
		verifyPageTitle(homePageTitle);
		
		logger.info("Click on 'Signup / Login' button");
		homePage.clickSignUpLoginLink();
		
		logger.info("Verify 'Login to your account' is visible");
		loginPage.verifyLoginToAccountText();
		
		logger.info("Enter incorrect email address and password and Click on login button");
		loginPage.loginToAutomationExercise(userEmail, userPassword);
		
		logger.info("Verify error 'Your email or password is incorrect!' is visible");
		loginPage.verifyIncorrectEmailPasswordMessageText(incorrectUserError);
		
	}
	
	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
