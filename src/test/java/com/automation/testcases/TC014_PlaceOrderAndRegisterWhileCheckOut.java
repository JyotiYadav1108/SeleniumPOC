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
import com.automation.exercise.pageobjects.RegistrationPage;

public class TC014_PlaceOrderAndRegisterWhileCheckOut extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	LoginPage loginPage;
	CartPage cartPage;
	PaymentPage paymentPage;
	RegistrationPage registrationPage;
	public static final Logger logger = LogManager.getLogger(TC014_PlaceOrderAndRegisterWhileCheckOut.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC014_VerifyPlaceOrderAndRegisterWhileCheckOut() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		paymentPage = new PaymentPage(driver);
		registrationPage = new RegistrationPage(driver);
		String homePageTitle = getData("Home Page Title");
		String accCreatedText = getData("AccountCreatedText");
		String accDeletedText = getData("AccountDeleteText");
		String orderPlacedText = getData("OrderPlacedText");
		String comment = getData("Comment");
		String userName = getData("TempUserName1");
		String userFullName = getData("TempUserFullName1");
		String userFirstName = getData("TempUserFirstName1");
		String userLastName = getData("TempUserLastName1");
		String userEmail = getData("TempUserEmail1");
		String userPassword = getData("TempUserPassword1");
		String userAddress1 = getData("TempUserAddress1");
		String userAddress2 = getData("TempUserAddress2");
		String userCompany = getData("TempUserCompany1");
		String userCity = getData("TempUserCity1");
		String userCompleteAddress = getData("TempUserCompleteAddress1");
		String userState = getData("TempUserState1");
		String userCountry = getData("TempUserCountry1");
		String userZipcode = getData("TempUserZipCode1");
		String userPhone = getData("TempUserPhone1");
		String userDOBDay = getData("UserDOBDD1");
		String userDOBMonth = getData("UserDOBMM1");
		String userDOBYear = getData("UserDOBYYYY1");
		String cardHolderName = getData("Card Holder Name");
		String cardNumber = getData("Card Number");
		String cardCVV = getData("Card CVV");
		String cardExpiryMonth = getData("Card Expiry Month");
		String cardExpiryYear = getData("Card Expiry Year");
//		List<Map<String, String>> expectedProducts = new ArrayList<>();
//		Map<String, String> product = new HashMap<>();
//        product.put("name", "Blue Top");
//        product.put("category", "Women > Tops");
//        product.put("price", "Rs. 500");
//        product.put("quantity", "1");
//        product.put("total", "Rs. 500");
//
//        expectedProducts.add(product);

		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Add those products to cart");
		productsPage.clickAddToCartForProduct("Men Tshirt");
		productsPage.clickOnContinueShopping();
		productsPage.clickAddToCartForProduct("Stylish Dress");
		productsPage.clickOnViewCartAfterProductsAreAddedToCart();
		
		logger.info("Verify that cart page is displayed");
		verifyUrlContains("/view_cart", "Verified that Cart page is displayed");
		
		logger.info("Click Proceed to Checkout");
		cartPage.clickProceedToCheckOutButton();
		
		logger.info("Click 'Register / Login' button");
		cartPage.clickRegisterLoginLink();
		
		logger.info("Enter details to register user before checkout");
		registrationPage.registerUser(userName, userEmail, "Mr.", userPassword, userDOBDay, userDOBMonth, userDOBYear, userFirstName, userLastName, userCompany, userAddress1, userAddress2, userCity, userCountry, userState, userZipcode, userPhone, false, false);
		registrationPage.verifyAccountCreatedText(accCreatedText);
		
		logger.info("Click on continue button");
		registrationPage.clickContinueButton();
		
		logger.info("Verify user is logged in after registration");
		loginPage.verifyLoggedInAsUserText(userName);
		
		logger.info("Click Carts");
		homePage.cartClick();
		
		logger.info("Click Proceed to Checkout");
		cartPage.clickProceedToCheckOutButton();
		
		logger.info("Verify Address Details");
		checkoutPage.verifyAddressDetails(userFullName, userCompany, userAddress1, userAddress2, userCompleteAddress, userCountry, userPhone);
		
		logger.info("Enter desciption in Comment Area");
		checkoutPage.enterDesciptionBoxDetails(comment);
		
		logger.info("Click on Place Order");
		checkoutPage.clickPlaceOder();
		
		logger.info("Enter Card Payment Details");
		paymentPage.enterCardPaymentDetails(cardHolderName, cardNumber, cardCVV, cardExpiryMonth, cardExpiryYear);
		
		logger.info("Verify success message 'Congratulations! Your order has been confirmed!'");
		paymentPage.verifyOrderPlacedText(orderPlacedText);
		
		logger.info("Click Delete button");
		registrationPage.clickDeleteAccount();
				
		logger.info("Verify account deleted and click on Continue");
		registrationPage.verifyAccountDeletedText(accDeletedText);
		registrationPage.clickContinueButton();
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}


}
