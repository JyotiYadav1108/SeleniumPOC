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
import com.automation.exercise.pageobjects.PaymentPage;
import com.automation.exercise.pageobjects.ProductsPage;

public class TC016_PlaceOrderAndLoginBeforeCheckout extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	LoginPage loginPage;
	CartPage cartPage;
	PaymentPage paymentPage;
	public static final Logger logger = LogManager.getLogger(TC016_PlaceOrderAndLoginBeforeCheckout.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC016_VerifyPlaceOrderAndLoginBeforeCheckout() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		paymentPage = new PaymentPage(driver);
		String homePageTitle = getData("Home Page Title");
		String orderPlacedText = getData("OrderPlacedText");
		String comment = getData("Comment");
		String userJyoti = getData("UserMainJyoti");
		String userTitleName = getData("UserTitleName1");
		String userAddress1 = getData("UserMainAddress1");
		String userAddress2 = getData("UserMainAddress2");
		String userCompany = getData("UserMainCompany");
		String userCompleteAddress = getData("UserMainCompleteAddress");
		String userCountry = getData("TempUserCountry1");
		String userPhone = getData("UserMainPhone");
		String cardHolderName = getData("Card Holder Name");
		String cardNumber = getData("Card Number");
		String cardCVV = getData("Card CVV");
		String cardExpiryMonth = getData("Card Expiry Month");
		String cardExpiryYear = getData("Card Expiry Year");
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
		
		logger.info("Click 'Signup / Login' button");
		homePage.clickSignUpLoginLink();
		
		logger.info("Fill email, password and click 'Login' button");
		loginPage.loginToAutomationExercise("jyoti.yadav@infogain.com", "qwerty");
		
		logger.info("Verify 'Logged in as username' at top");
		loginPage.verifyLoggedInAsUserText(userJyoti);
		
		logger.info("Add those products to cart");
		productsPage.clickAddToCartForProduct("Men Tshirt");
		productsPage.clickOnContinueShopping();
		productsPage.clickAddToCartForProduct("Stylish Dress");
		productsPage.clickOnViewCartAfterProductsAreAddedToCart();
		
		logger.info("Verify that cart page is displayed");
		verifyUrlContains("/view_cart", "Verified that Cart page is displayed");
		
		logger.info("Click Proceed to Checkout");
		cartPage.clickProceedToCheckOutButton();
		
		logger.info("Verify Address Details");
		checkoutPage.verifyAddressDetails(userTitleName, userCompany, userAddress1, userAddress2, userCompleteAddress, userCountry, userPhone);
		
		logger.info("Enter desciption in Comment Area");
		checkoutPage.enterDesciptionBoxDetails(comment);
		
		logger.info("Click on Place Order");
		checkoutPage.clickPlaceOder();
		
		logger.info("Enter Card Payment Details");
		paymentPage.enterCardPaymentDetails(cardHolderName, cardNumber, cardCVV, cardExpiryMonth, cardExpiryYear);
		
		logger.info("Verify success message 'Congratulations! Your order has been confirmed!'");
		paymentPage.verifyOrderPlacedText(orderPlacedText);
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}

}
