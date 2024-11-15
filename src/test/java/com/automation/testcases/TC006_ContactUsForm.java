package com.automation.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.ContactUsPage;
import com.automation.exercise.pageobjects.HomePage;

public class TC006_ContactUsForm extends TestBase {
	
	HomePage homePage;
	ContactUsPage contactUsPage;
	public static final Logger logger = LogManager.getLogger(TC006_ContactUsForm.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC006_VerifyFillContactUsForm() throws Exception {
		homePage = new HomePage(driver);
		contactUsPage = new ContactUsPage(driver);
		String homePageTitle = getData("Home Page Title");
		String userName = getData("UserMainJyoti");
		String userEmail = getData("UserMainEmail");
		String subject = getData("ContactFormSubject");
		String message = getData("ContactFormMessage");
		String userPassword = getData("UserMainPassword");
		String file = System.getProperty("user.dir") + "\\src\\main\\resources\\com\\exercise\\testdata\\contactusform.txt";
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Click on Contact Us Link on Home Page");
		homePage.contactUsClick();
		
		logger.info("Verify Get In Touch Text on Contact Us Form Page");
		contactUsPage.verifyGetInTouchText();
		
		logger.info("Fill details in Contact Us Form");
		contactUsPage.contactUsFormFillDetails(userName, userEmail, subject, message, file);
		
		logger.info("Verify success message for submitting the form");
		contactUsPage.verifySuccessMessage();
		
		logger.info("Click on Home Page button");
		contactUsPage.clickBackHomeButton();
		
		logger.info("Verify Home Page Title after submitting Contact Us Form");
		verifyPageTitle(homePageTitle);
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
