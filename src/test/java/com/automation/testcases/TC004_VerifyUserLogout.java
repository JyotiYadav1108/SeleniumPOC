package com.automation.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.LoginPage;

public class TC004_VerifyUserLogout extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	public static final Logger logger = LogManager.getLogger(TC004_VerifyUserLogout.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC004_VerifyLogoutUser() throws Exception {
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		String homePageTitle = getData("Home Page Title");
		String userName = getData("UserMainJyoti");
		String userEmail = getData("UserMainEmail");
		String userPassword = getData("UserMainPassword");
		
		logger.info("Verify that home page is visible successfully");
		verifyPageTitle(homePageTitle);
		
		logger.info("Clicking on Signup/Login link from Home Page");
		hardWait(1000);
		homePage.clickSignUpLoginLink();
		
		logger.info("Verify 'Login to your account' text on Login Page");
		loginPage.verifyLoginToAccountText();
		
		logger.info("Login to Account using Valid User Credentials");
		loginPage.loginToAutomationExercise(userEmail, userPassword);
		
		logger.info("Verify that after login, Home page has 'Logged in as username' text");
		loginPage.verifyLoggedInAsUserText(userName);
		
		logger.info("Clicking on Logout from Home Page");
		homePage.logoutClick();
		
		logger.info("Verify 'Login to your account' text on Login Page after Logout from User");
		loginPage.verifyLoginToAccountText();
	}
	
	@AfterMethod
	public void tearDown() {
		closeBrowser();
	}

}
