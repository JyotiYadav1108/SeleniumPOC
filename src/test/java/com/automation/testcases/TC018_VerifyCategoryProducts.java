package com.automation.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.exercise.TestBase;
import com.automation.exercise.pageobjects.CartPage;
import com.automation.exercise.pageobjects.CheckoutPage;
import com.automation.exercise.pageobjects.HomePage;
import com.automation.exercise.pageobjects.LoginPage;
import com.automation.exercise.pageobjects.ProductsPage;

public class TC018_VerifyCategoryProducts extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	LoginPage loginPage;
	CartPage cartPage;
	public static final Logger logger = LogManager.getLogger(TC018_VerifyCategoryProducts.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC018_VerifyCategoryOnProducts() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		String productsPageTitle = getData("Product Page Title");
		List<String> expectedCategories = Arrays.asList("Women", "Men", "Kids");

		logger.info("Verify that categories are visible on left side bar");
		boolean isCategoryListCorrect = homePage.verifyCategoryNames(expectedCategories);
		if (isCategoryListCorrect) {
		    System.out.println("Category names are verified and correct.");
		} else {
		    System.out.println("Category names do not match.");
		}
		
		logger.info("Click on 'Women' category");
		homePage.clickCategory("Women");
		
		logger.info("Click on 'Dress' subcategory under 'Women'");
		homePage.clickSubCategoryProduct("Women", "Dress ");
		
		logger.info("Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'");
		productsPage.verifyProductCategoryHeader("WOMEN - DRESS PRODUCTS");
		
		logger.info("Click on 'Men' category");
		homePage.clickCategory("Men");
		
		logger.info("Click on 'Jeans' subcategory under 'Men'");
		homePage.clickSubCategoryProduct("Men", "Jeans");
		
		logger.info("Verify that category page is displayed and confirm text 'MEN -  JEANS PRODUCTS'");
		productsPage.verifyProductCategoryHeader("MEN - JEANS PRODUCTS");
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
