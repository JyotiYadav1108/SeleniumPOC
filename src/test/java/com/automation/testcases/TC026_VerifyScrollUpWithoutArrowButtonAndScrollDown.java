package com.automation.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;

public class TC026_VerifyScrollUpWithoutArrowButtonAndScrollDown extends TestBase {
	
	HomePage homePage;
	public static final Logger logger = LogManager.getLogger(TC026_VerifyScrollUpWithoutArrowButtonAndScrollDown.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC011_VerifySubscriptionOnCartsPage() throws IOException {
		homePage = new HomePage(driver);
		String homePageTitle = getData("Home Page Title");
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Scroll down page to bottom");
		scrollDownOnWebPage();
		
		logger.info("Verify 'SUBSCRIPTION' is visible");
		homePage.verifySubsciptionTextOnHomePage();
		
		logger.info("Scroll up page to top");
		scrollUpOnWebPage();
		
		logger.info("Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen");
		homePage.verifyPageHeaderForAutomationExercise();
			
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}



}
