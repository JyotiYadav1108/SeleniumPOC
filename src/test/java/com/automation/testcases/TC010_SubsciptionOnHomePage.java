package com.automation.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;

public class TC010_SubsciptionOnHomePage extends TestBase {
	
	HomePage homePage;
	public static final Logger logger = LogManager.getLogger(TC010_SubsciptionOnHomePage.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC010_VerifySubsciptionOnHomePage() throws IOException {
		homePage = new HomePage(driver);
		String homePageTitle = getData("Home Page Title");
		String userEmail = getData("UserMainEmail");
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Scroll down to footer and Verify Subscription Text");
		homePage.verifySubsciptionTextOnHomePage();
		
		logger.info("Enter email address in input and click arrow button");
		homePage.fillDetailsInSubscriptionBox(userEmail);
		
		logger.info("Verify success message 'You have been successfully subscribed!' is visible");
		homePage.verifySuccessMessageAfterSubscribing();		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}


}
