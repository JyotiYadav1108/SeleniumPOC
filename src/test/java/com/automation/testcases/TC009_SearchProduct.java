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

public class TC009_SearchProduct extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	public static final Logger logger = LogManager.getLogger(TC009_SearchProduct.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC009_VerifySearchProduct() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		String homePageTitle = getData("Home Page Title");
		String productsPageTitle = getData("Product Page Title");
		String tops = getData("Tops");
		
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Click on Products Page");
		homePage.productClick();
		
		logger.info("Verify user is navigated to All Products page successfully");
		verifyPageTitle(productsPageTitle);
		
		logger.info("Verify All Products present on page");
		productsPage.verifyAllProductsText();
		
		logger.info("Enter Product to be searched in TextBox and Click on Search");
		productsPage.searchProduct(tops);
		
		logger.info("Verify 'SEARCHED PRODUCTS' is visible");
		productsPage.verifySearchedProductsText();
		
		logger.info("Verify all the products related to search are visible");
		productsPage.verifySearchedProductsAreTops();
		
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}


}
