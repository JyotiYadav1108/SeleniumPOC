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

public class TC019_ViewAndCartBrandProducts extends TestBase {
	
	HomePage homePage;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	LoginPage loginPage;
	CartPage cartPage;
	public static final Logger logger = LogManager.getLogger(TC019_ViewAndCartBrandProducts.class.getName());
	
	@BeforeMethod
	public void setUp() throws IOException {
		init();
	}
	
	@Test
	public void TC019_VerifyViewAndCartBrandProducts() throws IOException {
		homePage = new HomePage(driver);
		productsPage = new ProductsPage(driver);
		checkoutPage = new CheckoutPage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		String productsPageTitle = getData("Product Page Title");
		//List<String> expectedBrands = Arrays.asList("\nPOLO", "\nH&M", "\nMADAME", "\nMAST & HARBOUR", "\nBABYHUG", "\nALLEN SOLLY JUNIOR", "\nKOOKIE KIDS", "\nBIBA");
		List<String> expectedBrands = Arrays.asList("POLO", "H&M", "MADAME", "MAST & HARBOUR", "BABYHUG", "ALLEN SOLLY JUNIOR", "KOOKIE KIDS", "BIBA");
		
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

		logger.info("Verify that Brands are visible on left side bar");
		boolean isBrandListCorrect = productsPage.verifyBrandNames(expectedBrands);
		if (isBrandListCorrect) {
		    System.out.println("Brand names are verified and correct.");
		} else {
		    System.out.println("Brand names do not match.");
		}
		
		logger.info("Click on any brand name");
		productsPage.clickBrand("Polo");
		
		logger.info("Verify that user is navigated to brand page and brand products are displayed");
		verifyUrlContains("/brand_products/Polo", "Verified that user is navigated to brand page and brand products are displayed");
		productsPage.verifyBrandSpecificProductsText("POLO");
		
		logger.info("On left side bar, click on any other brand link");
		productsPage.clickBrand("Babyhug");
		
		logger.info("Verify that user is navigated to brand page and brand products are displayed");
		verifyUrlContains("/brand_products/Babyhug", "Verified that user is navigated to brand page and brand products are displayed");
		productsPage.verifyBrandSpecificProductsText("BABYHUG");
		
	}

	@AfterMethod
	public void tearDown()  {
		 
		closeBrowser();
	}


}
