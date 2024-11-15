package com.automation.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.CartPage;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.ProductsPage;

public class TC011_VerifySubscriptionOnCartPage extends TestBase{
	
	HomePage homePage;
	CartPage cartsPage;
	public static final Logger logger = LogManager.getLogger(TC011_VerifySubscriptionOnCartPage.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC011_VerifySubscriptionOnCartsPage() throws IOException {
		homePage = new HomePage(driver);
		cartsPage = new CartPage(driver);
		String homePageTitle = getData("Home Page Title");
		String userEmail = getData("UserMainEmail");
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Click on Carts Page");
		homePage.cartClick();
		
		logger.info("Scroll down to footer and Verify Subscription Text");
		cartsPage.verifySubsciptionTextOnCartsPage();
		
		logger.info("Enter email address in input and click arrow button");
		cartsPage.fillDetailsInSubscriptionBox(userEmail);
		
		logger.info("Verify success message 'You have been successfully subscribed!' is visible");
		cartsPage.verifySuccessMessageAfterSubscribing();		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}


}
