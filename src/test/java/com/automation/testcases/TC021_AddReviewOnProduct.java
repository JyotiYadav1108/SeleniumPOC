package com.automation.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.ProductsPage;

public class TC021_AddReviewOnProduct extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	public static final Logger logger = LogManager.getLogger(TC021_AddReviewOnProduct.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC021_VerifyAddReviewOnProduct() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		String productsPageTitle = getData("Product Page Title");
		String reviewerName = getData("ReviewerName");
		String reviewerEmail = getData("ReviewerEmail");
		String reviewerMessage = getData("ReviewerMessage");

        Map<String, String> product1 = new HashMap<>();
        product1.put("name", "Fancy Green Top");
		
		logger.info("Click on 'Products' button");
		homePage.productClick();
		
		logger.info("Verify user is navigated to All Products page successfully");
		verifyPageTitle(productsPageTitle);
		
		logger.info("Verify All Products present on page");
		productsPage.verifyAllProductsText();
		
		logger.info("Click on 'View Product' button");
		homePage.clickOnViewProduct(product1.get("name"));
		
		logger.info("Verify 'Write Your Review' is visible");
		productsPage.verifyWriteYourReviewText();
		
		logger.info("Enter name, email and review and Submit");
		productsPage.writeProductReviewAndSubmit(reviewerName, reviewerEmail, reviewerMessage);
		
		logger.info("Verify success message 'Thank you for your review.'");
		productsPage.verifyReviewSuccessfullySubmittedText();
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
