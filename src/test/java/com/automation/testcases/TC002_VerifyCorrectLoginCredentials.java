package com.automation.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

 
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.LoginPage;
import com.aventstack.extentreports.Status;


public class TC002_VerifyCorrectLoginCredentials extends TestBase {
	
	HomePage homePage = new HomePage(driver);
	LoginPage loginPage = new LoginPage(driver);
	
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC002_VerifyValidUserLogin() throws Exception {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		String homePageTitle = getData("Home Page Title");
		String userName = getData("UserMainJyoti");
		String userEmail = getData("UserMainEmail");
		String userPassword = getData("UserMainPassword");
		
		logger.info("Verify that home page is visible successfully");
		verifyPageTitle(homePageTitle);
	
		logger.info("Click on 'Signup / Login' button");
		homePage.clickSignUpLoginLink();
		
		logger.info("Verify 'Login to your account' is visible");
		loginPage.verifyLoginToAccountText();
		
		logger.info("Enter correct email address and password and Click on login button");
		loginPage.loginToAutomationExercise(userEmail, userPassword);
		
		logger.info("Verify that 'Logged in as username' is visible");
		loginPage.verifyLoggedInAsUserText(userName);
		
	}

	@AfterMethod
	public void tearDown()  {
		closeBrowser();
	}

}
