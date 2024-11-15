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
import com.automation.exercise.pageobjects.CheckoutPage;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.ProductsPage;

import dev.failsafe.internal.util.Assert;

public class TC013_VerifyProductQuantityInCart extends TestBase{
	
	HomePage homePage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	public static final Logger logger = LogManager.getLogger(TC013_VerifyProductQuantityInCart.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC013_VerifyProductQuantityCart() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		String productsPageTitle = getData("Product Page Title");
		String summerWhiteTop = getData("SummerWhiteTop");
		
		logger.info("Click on 'Products' button");
		homePage.productClick();
		
		logger.info("Verify user is navigated to All Products page successfully");
		verifyPageTitle(productsPageTitle);
		
		logger.info("Click on 'View Product' button");
		homePage.clickOnViewProduct(summerWhiteTop);
		
		logger.info("Verify that detail is visible: product name, category, price, availability, condition, brand");
		verifyUrlContains("/product_details", "Verified that product details page is open");
		productsPage.verifyProductDetailsAreVisible();
		
		logger.info("Increase quantity to 4");
		productsPage.enterProductQuantity("4");
		
		logger.info("Click add to cart");
		productsPage.clickAddToCart();
		
		logger.info("Click on view cart");
		productsPage.clickViewCart();
		
		logger.info("Verify that product is displayed in cart page with exact quantity");
		boolean isQuantityCorrect = checkoutPage.verifyProductQuantity(summerWhiteTop, 4);
		org.testng.Assert.assertTrue(isQuantityCorrect, "Verified product quantity is correct in Cart");

	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
