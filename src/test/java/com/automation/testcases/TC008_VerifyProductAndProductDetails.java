package com.automation.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.ProductsPage;
import com.automation.exercise.pageobjects.TestCasesPage;

public class TC008_VerifyProductAndProductDetails extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	public static final Logger logger = LogManager.getLogger(TC008_VerifyProductAndProductDetails.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC008_VerifyProductsPageAndProductDetails() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		String homePageTitle = getData("Home Page Title");
		String productsPageTitle = getData("Product Page Title");
		
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Click on Products Page");
		homePage.productClick();
		
		logger.info("Verify user is navigated to All Products page successfully");
		verifyPageTitle(productsPageTitle);
		
		logger.info("Verify All Products present on page");
		productsPage.verifyAllProductsText();
		
		logger.info("Click on first product -> View Product Link");
		productsPage.clickViewProductLinkOfFirstProduct();
		
		logger.info("Verify that detail is visible: product name, category, price, availability, condition, brand");
		productsPage.verifyProductDetailsAreVisible();
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
