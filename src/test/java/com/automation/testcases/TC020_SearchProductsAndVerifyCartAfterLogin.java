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
import com.automation.exercise.pageobjects.LoginPage;
import com.automation.exercise.pageobjects.ProductsPage;

public class TC020_SearchProductsAndVerifyCartAfterLogin extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	LoginPage loginPage;
	CartPage cartPage;
	public static final Logger logger = LogManager.getLogger(TC020_SearchProductsAndVerifyCartAfterLogin.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC020_VerifySearchProductsAndCartAfterLogin() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		String productsPageTitle = getData("Product Page Title");
		String tops = getData("Tops");
		String userEmail = getData("UserMainEmail");
		String userPassword = getData("UserMainPassword");

		List<Map<String, String>> expectedProducts = new ArrayList<>();
		Map<String, String> product1 = new HashMap<>();
        product1.put("name", "Blue Top");
        product1.put("category", "Women > Tops");
        product1.put("price", "Rs. 500");
        product1.put("quantity", "1");
        product1.put("total", "Rs. 500");

        Map<String, String> product2 = new HashMap<>();
        product2.put("name", "Winter Top");
        product2.put("category", "Women > Tops");
        product2.put("price", "Rs. 600");
        product2.put("quantity", "1");
        product2.put("total", "Rs. 600");

        expectedProducts.add(product1);
        expectedProducts.add(product2);
		
		logger.info("Click on 'Products' button");
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
		
		logger.info("Add those products to cart");
		productsPage.clickAddToCartForProduct(product1.get("name"));
		productsPage.clickOnContinueShopping();
		productsPage.clickAddToCartForProduct(product2.get("name"));
		productsPage.clickOnViewCartAfterProductsAreAddedToCart();
		
		logger.info("Review Order");
		boolean allProductsVerified = checkoutPage.verifyProducts(expectedProducts);
		System.out.println("Verification Result: " + allProductsVerified);
		
		logger.info("Click 'Signup / Login' button and submit login details");
		checkoutPage.clickSignUpAndLogin();
		loginPage.loginToAutomationExercise(userEmail, userPassword);
		
		logger.info("Again Click on 'Carts' button");
		homePage.cartClick();
		
		logger.info("Review Order after Login");
		boolean allProductsVerifiedAfterLogin = checkoutPage.verifyProducts(expectedProducts);
		System.out.println("Verification Result: " + allProductsVerifiedAfterLogin);
		
		logger.info("Remove all products that have been added");
		checkoutPage.deleteProductByName(product1.get("name"));
		checkoutPage.deleteProductByName(product2.get("name"));
		
		logger.info("Verify 'Cart is empty! Click here to buy products.' is visible");
		cartPage.verifyEmptyCartDisplayText();
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}


}
