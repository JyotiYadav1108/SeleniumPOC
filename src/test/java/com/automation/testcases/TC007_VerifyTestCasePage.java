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
import com.automation.exercise.pageobjects.TestCasesPage;

public class TC007_VerifyTestCasePage extends TestBase{
	
	HomePage homePage;
	TestCasesPage testCasesPage;
	public static final Logger logger = LogManager.getLogger(TC007_VerifyTestCasePage.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC007_VerifyTestCasesPage() throws Exception {
		homePage = new HomePage(driver);
		testCasesPage = new TestCasesPage(driver);
		String homePageTitle = getData("Home Page Title");
		String testCasesPageTitle = getData("TestCasesPageTitle");
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Click on Test Cases");
		homePage.testCasesClick();
		
		logger.info("Verify user is navigated to test cases page successfully");
		verifyPageTitle(testCasesPageTitle);
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
