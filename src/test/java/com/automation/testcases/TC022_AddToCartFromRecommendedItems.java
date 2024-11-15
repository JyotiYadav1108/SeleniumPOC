package com.automation.testcases;

import java.io.IOException;
import java.util.ArrayList;
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
import com.automation.exercise.pageobjects.ProductsPage;

public class TC022_AddToCartFromRecommendedItems extends TestBase {
	
	HomePage homePage;
	CartPage cartPage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	public static final Logger logger = LogManager.getLogger(TC022_AddToCartFromRecommendedItems.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC022_VerifyAddToCartFromRecommendedItems() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		cartPage = new CartPage(driver);
		String homePageTitle = getData("Home Page Title");

        Map<String, String> product1 = new HashMap<>();
        product1.put("name", "Summer White Top");
        product1.put("category", "Women > Tops");
        product1.put("price", "Rs. 400");
        product1.put("quantity", "1");
        product1.put("total", "Rs. 400");
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Scroll to bottom of page");
		scrollDownOnWebPage();
		
		logger.info("Verify 'RECOMMENDED ITEMS' are visible");
		homePage.verifyRecommendedItemsIsVisible();
		
		logger.info("Click on 'Add To Cart' on Recommended product");
		homePage.clickAddToCardFromRecommendedItems();
		
		logger.info("Click 'View Cart'");
		productsPage.clickOnViewCartAfterProductsAreAddedToCart();
		
		logger.info("Review Order");
		cartPage.verifyItemsInCartArePresent();
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
