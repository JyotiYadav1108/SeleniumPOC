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

public class TC012_AddProductsToCart extends TestBase{
	
	HomePage homePage;
	ProductsPage productsPage;
	public static final Logger logger = LogManager.getLogger(TC012_AddProductsToCart.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC012_VerifyAddProductsToCart() throws IOException {
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
		
		logger.info("Hover over first product and click 'Add to cart'");
		productsPage.clickAddToCartForProduct("Men Tshirt");
		
		logger.info("Click 'Continue Shopping' button");
		productsPage.clickOnContinueShopping();
		
		logger.info("Hover over first product and click 'Add to cart'");
		productsPage.clickAddToCartForProduct("Stylish Dress");
		
		logger.info("Click on View Cart");
		productsPage.clickOnViewCartAfterProductsAreAddedToCart();
		
		logger.info("Verify product details on Cart Page");
		productsPage.verifyCartDetails();
		
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}


}
