package com.automation.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
 
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

public class TC024_DownloadInvoiceAfterPurchaseOrder extends TestBase {
	
	HomePage homePage;
	CartPage cartPage;
	LoginPage loginPage;
	RegistrationPage registrationPage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	PaymentPage paymentPage;
	public static final Logger logger = LogManager.getLogger(TC024_DownloadInvoiceAfterPurchaseOrder.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC024_VerifyDownloadInvoiceAfterPurchaseOrder() throws IOException {
		homePage = new HomePage(driver);
		cartPage = new CartPage(driver);
		loginPage = new LoginPage(driver);
		registrationPage = new RegistrationPage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		paymentPage = new PaymentPage(driver);
		String homePageTitle = getData("Home Page Title");
		String accCreatedText = getData("AccountCreatedText");
		String accDeletedText = getData("AccountDeleteText");
		String userName = getData("TempUserName1");
		String userFullName = getData("TempUserFullName1");
		String userFirstName = getData("TempUserFirstName1");
		String userLastName = getData("TempUserLastName1");
		String userEmail = getData("TempUserEmail1");
		String userPassword = getData("TempUserPassword1");
		String userAddress1 = getData("TempUserAddress1");
		String userAddress2 = getData("TempUserAddress2");
		String userCompleteAddress = getData("TempUserCompleteAddress1");
		String userCompany = getData("TempUserCompany1");
		String userCity = getData("TempUserCity1");
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
		
		List<Map<String, String>> expectedProducts = new ArrayList<>();

        Map<String, String> product1 = new HashMap<>();
        product1.put("name", "Blue Top");
        product1.put("category", "Women > Tops");
        product1.put("price", "Rs. 500");
        product1.put("quantity", "4");
        product1.put("total", "Rs. 2000");

        Map<String, String> product2 = new HashMap<>();
        product2.put("name", "Men Tshirt");
        product2.put("category", "Men > Tshirts");
        product2.put("price", "Rs. 400");
        product2.put("quantity", "1");
        product2.put("total", "Rs. 400");

        expectedProducts.add(product1);
        expectedProducts.add(product2);
		
		logger.info("Verify Home Page Title");
		verifyPageTitle(homePageTitle);
		
		logger.info("Add product 1 to Cart");
		productsPage.clickAddToCartForProduct("Men Tshirt");
		
		logger.info("Click 'Continue Shopping' button");
		productsPage.clickOnContinueShopping();
		
		logger.info("Add product 2 to Cart");
		productsPage.clickAddToCartForProduct("Winter Top");
		
		logger.info("Click 'Cart' button");
		productsPage.clickOnViewCartAfterProductsAreAddedToCart();
		
		logger.info("Click Proceed to Checkout");
		cartPage.clickProceedToCheckOutButton();
		
		logger.info("Click 'Register / Login' button");
		checkoutPage.clickRegisterLoginLinkOnCheckout();
		
		logger.info("Enter details to register user before checkout");
		registrationPage.registerUser(userName, userEmail, "Mr.", userPassword, userDOBDay, userDOBMonth, userDOBYear, userFirstName, userLastName, userCompany, userAddress1, userAddress2, userCity, userCountry, userState, userZipcode, userPhone, false, false);
		registrationPage.verifyAccountCreatedText(accCreatedText);
		
		logger.info("Click on continue button");
		registrationPage.clickContinueButton();
		
		logger.info("Verify user is logged in after registration");
		loginPage.verifyLoggedInAsUserText(userName);
		
		logger.info("Click on Carts Page");
		homePage.cartClick();
		
		logger.info("Click Proceed to Checkout");
		cartPage.clickProceedToCheckOutButton();
		
		logger.info("Verify Address Details");
		checkoutPage.verifyAddressDetails(userFullName, userCompany, userAddress1, userAddress2, userCompleteAddress, userCountry, userPhone);
		
		logger.info("Review Order");
		boolean allProductsVerified = checkoutPage.verifyProducts(expectedProducts);
		System.out.println("Verification Result: " + allProductsVerified);
		
		logger.info("Enter desciption in Comment Area");
		checkoutPage.enterDesciptionBoxDetails("Description");
		
		logger.info("Click on Place Order");
		checkoutPage.clickPlaceOder();
		
		logger.info("Enter Card Payment Details");
		paymentPage.enterCardPaymentDetails(cardHolderName, cardNumber, cardCVV, cardExpiryMonth, cardExpiryYear);
		
		logger.info("Verify success message 'Congratulations! Your order has been confirmed!'");
		paymentPage.verifyOrderPlacedText("Congratulations! Your order has been confirmed!");
				
		logger.info("Click 'Download Invoice' button and verify invoice is downloaded successfully");
		paymentPage.clickDownloadInvoice();
		Assert.assertTrue(isFileDownloaded("invoice.txt"));
		
		logger.info("Click 'Continue' button");
		paymentPage.clickContinueButton();
		
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
