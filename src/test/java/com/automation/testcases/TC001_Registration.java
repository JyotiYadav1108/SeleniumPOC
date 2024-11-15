package com.automation.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.RegistrationPage;
import com.exercise.utils.ReportUtils;

public class TC001_Registration extends TestBase{
	
	HomePage homePage;
	RegistrationPage registrationPage = new RegistrationPage(driver);
	public static final Logger logger = LogManager.getLogger(TC001_Registration.class.getName());
	
	@BeforeClass
    public void setupClass() {
        ReportUtils.initReport("TestReport.html");
    }

	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
		ReportUtils.startTest("Registration Test Case");
	}
	
	@Test
	public void TC001_RegisterUser() throws Exception {
		homePage = new HomePage(driver);
		registrationPage = new RegistrationPage(driver);
		String homePageTitle = getData("Home Page Title");
		String accCreatedText = getData("AccountCreatedText");
		String accDeletedText = getData("AccountDeleteText");
		String userName = getData("TempUserName1");
		String userFullName = getData("TempUserFullName1");
		String userFirstName = getData("TempUserFirstName1");
		String userLastName = getData("TempUserLastName1");
		String userEmail = getData("TempUserEmail1");
		String userPassword = getData("TempUserPassword1");
		String userAddress1 = getData("TempUserAddress1");
		String userAddress2 = getData("TempUserAddress2");
		String userCompany = getData("TempUserCompany1");
		String userCity = getData("TempUserCity1");
		String userState = getData("TempUserState1");
		String userCountry = getData("TempUserCountry1");
		String userZipcode = getData("TempUserZipCode1");
		String userPhone = getData("TempUserPhone1");
		String userDOBDay = getData("UserDOBDD1");
		String userDOBMonth = getData("UserDOBMM1");
		String userDOBYear = getData("UserDOBYYYY1");
		
		verifyPageTitle(homePageTitle);
		homePage.clickSignUpLoginLink();
		logger.info("Enter all the registration details in form");
		registrationPage.registerUser(userName, userEmail, "Mr.", userPassword, userDOBDay, userDOBMonth, userDOBYear, userFirstName, userLastName, userCompany, userAddress1, userAddress2, userCity, userCountry, userState, userZipcode, userPhone, false, false);
		registrationPage.verifyAccountCreatedText(accCreatedText);
		
		logger.info("Click on continue button");
		registrationPage.clickContinueButton();
		registrationPage.verifyLoggedInUser("Will Smith");
		
		
		logger.info("Click on delete account");
		registrationPage.clickDeleteAccount();
		registrationPage.verifyAccountDeletedText(accDeletedText);
		
		logger.info("Click on continue button");
		registrationPage.clickContinueButton();
		ReportUtils.log("Navigating to example.com");
    //    Assert.assertEquals(driver.getTitle(), "Example Domain");
        ReportUtils.captureScreenshot(driver, "HomePage");
	}

	@AfterMethod
	public void tearDown()  {
		closeBrowser();
		ReportUtils.endTest();
	}
}
