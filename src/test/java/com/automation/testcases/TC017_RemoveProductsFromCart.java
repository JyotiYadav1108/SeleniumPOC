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

public class TC017_RemoveProductsFromCart extends TestBase{
	
	HomePage homePage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	LoginPage loginPage;
	CartPage cartPage;
	public static final Logger logger = LogManager.getLogger(TC017_RemoveProductsFromCart.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC017_VerifyRemoveProductsFromCart() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		String homePageTitle = getData("Home Page Title");
		List<Map<String, String>> expectedProducts = new ArrayList<>();
		Map<String, String> product = new HashMap<>();
        product.put("name", "Blue Top");
        product.put("category", "Women > Tops");
        product.put("price", "Rs. 500");
        product.put("quantity", "1");
        product.put("total", "Rs. 500");

        expectedProducts.add(product);

		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Add those products to cart");
		productsPage.clickAddToCartForProduct("Blue Top");
		productsPage.clickOnContinueShopping();
		productsPage.clickAddToCartForProduct("Sleeveless Dress");
		productsPage.clickOnViewCartAfterProductsAreAddedToCart();
		
		logger.info("Verify that cart page is displayed");
		verifyUrlContains("/view_cart", "Verified that Cart page is displayed");
		
		logger.info("Click 'X' button corresponding to particular product");
		checkoutPage.deleteProductByName("Sleeveless Dress");
		
		logger.info("Verify that product is removed from the cart");
		boolean allProductsVerified = checkoutPage.verifyProducts(expectedProducts);
		System.out.println("Verification Result: " + allProductsVerified);
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
