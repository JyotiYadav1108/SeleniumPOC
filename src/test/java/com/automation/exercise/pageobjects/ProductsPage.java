package com.automation.exercise.pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.exercise.TestBase;

public class ProductsPage extends TestBase{
	
	WebDriver driver;
	WebDriverWait wait;
	public static final Logger logger = LogManager.getLogger(ProductsPage.class.getName());
	
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'contact-form']/h2")
	WebElement getInTouchText;
	
	@FindBy(xpath = "//div[@class = 'features_items']/h2")
	WebElement allProductsText;
	
	@FindBy(xpath = "(//div[@class = 'features_items']//div[@class = 'col-sm-4'])[1]//div[@class = 'choose']//a")
	WebElement viewProductOfFirstProduct;
	
	@FindBy(xpath = "//div[@class = 'product-information']/h2")
	WebElement productDetail;
	
	@FindBy(xpath = "//div[@class = 'product-information']/p")
	WebElement productCategory;
	
	@FindBy(xpath = "//div[@class = 'product-information']/span/span")
	WebElement productPrice;
	
	@FindBy(xpath = "//div[@class = 'product-information']/p[2]")
	WebElement productAvailability;
	
	@FindBy(xpath = "//div[@class = 'product-information']/p[3]")
	WebElement productCondition;
	
	@FindBy(xpath = "//div[@class = 'product-information']/p[4]")
	WebElement productBrand;
	
	@FindBy(xpath = "//input[@id='search_product']")
	WebElement searchProductTextBox;
	
	@FindBy(xpath = "//button[@id='submit_search']")
	WebElement searchButton;
	
	@FindBy(xpath = "//div[@class = 'features_items']/h2")
	WebElement searchedProducts;
	
	@FindBy(xpath = "//div[@class = 'productinfo text-center']/p[contains(text(), 'Top')]")
	WebElement searchedProductsTop;
	
	@FindBy(xpath = "//button[text() = 'Continue Shopping']")
	WebElement continueShoppingButton;
	
	@FindBy(xpath = "//a[@href = '/view_cart']/u")
	WebElement viewCartOnProductsPage;
	
	@FindBy(xpath = "//a[@href = '#reviews']")
	WebElement writeYourReviewText;
	
	@FindBy(id = "name")
	WebElement reviewerName;
	
	@FindBy(id = "email")
	WebElement reviewerEmail;
	
	@FindBy(xpath = "//button[@id = 'button-review']")
	WebElement submitReviewButton;
	
	@FindBy(xpath = "//textarea[@name = 'review']")
	WebElement reviewerText;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(@class, 'alert')]/span[contains(text(), 'Thank you for your review.')]")
	WebElement successAlertMessage;
	
	@FindBy(xpath = "//div[@class = 'features_items']/h2")
	WebElement brandSpecificProductsText;
	
	@FindBy(xpath = "//h2[@class='title text-center']")
	WebElement productCategoryHeadingText;
	
	@FindBy(xpath = "//input[@id='quantity']")
	WebElement productQuantity;
	
	@FindBy(xpath = "//button[@class = 'btn btn-default cart']")
	WebElement addToCartButton;
	
	@FindBy(xpath = "//a/u")
	WebElement viewCartLink;

	/*
	 * Method to Verify 'All PRODUCTS' text
	 */
	public void verifyAllProductsText() {
		Assert.assertEquals(allProductsText.getText(), "ALL PRODUCTS");
	}
	
	/*
	 * Method to Click 'View Product' of first product on Products Page
	 */
	public void clickViewProductLinkOfFirstProduct() {
		viewProductOfFirstProduct.click();
	}
	
	/*
	 * Method to verify Products Details are visible
	 */
	public void verifyProductDetailsAreVisible() {
		if(productDetail.isDisplayed()
			&&	productCategory.isDisplayed() 
			&& productAvailability.isDisplayed()
			&& productCondition.isDisplayed()
			&& productBrand.isDisplayed()
			&& productPrice.isDisplayed())
		System.out.println("Verified that detail is visible: product name, category, price, availability, condition, brand");
	}
	
	/*
	 * Method to search product from search bar
	 */
	public void searchProduct(String productName) {
		searchProductTextBox.sendKeys(productName);
		searchButton.click();
	}

	/*
	 * Method to Verify 'Searched Products' text
	 */
	public void verifySearchedProductsText() {
		Assert.assertEquals(searchedProducts.getText(), "SEARCHED PRODUCTS");
	}
	
	/*
	 * Method to Verify 'Searched Products' text are 'Tops'
	 */
	public void verifySearchedProductsAreTops() {
		Assert.assertTrue(searchedProductsTop.isDisplayed());
	}
	
	/*
	 * Method to Click 'Continue Shopping' after items are added to the Cart
	 */
	public void clickOnContinueShopping() {
		waitForElementToBeClickable(Duration.ofSeconds(100), continueShoppingButton);
		continueShoppingButton.click();
	}
	
	/*
	 * Method to Click 'View Cart' after items are added to the Cart
	 */
	public void clickOnViewCartAfterProductsAreAddedToCart() {
		//WebElement viewCartOnProductPageElement = driver.findElement(By.ByXPath(viewCartOnProductsPage));
		waitForElementToBeClickable(Duration.ofSeconds(100), viewCartOnProductsPage);
		viewCartOnProductsPage.click();
	}
	
	/*
	 * Method to Verify Cart Details
	 */	
	public void verifyCartDetails() {
		List<WebElement> productRows = driver.findElements(By.xpath("//table[@id='cart_info_table']/tbody/tr[contains(@id, 'product')]"));

        // Loop through each product row and verify details
        for (WebElement row : productRows) {
            // Example: Verify details such as product name, price, or quantity
            String cartPrice = row.findElement(By.xpath(".//td[@class='cart_price']")).getText();
            String cartQuantity = row.findElement(By.xpath(".//td[@class='cart_quantity']")).getText();
            String cartTotal = row.findElement(By.xpath(".//td[@class='cart_total']")).getText();

            // Print or assert the details for verification
            System.out.println("Cart Price: " + cartPrice);
            System.out.println("Cart Quantity: " + cartQuantity);
            System.out.println("Cart Total : " + cartTotal);

	}
	}
	
	/*
	 * Method to Verify 'Write Your Review' text
	 */
	public void verifyWriteYourReviewText() {
		Assert.assertEquals(writeYourReviewText.getText(), "WRITE YOUR REVIEW");
	}	
	
	/*
	 * Method to Enter Product Review Details and Click Submit
	 */
	public void writeProductReviewAndSubmit(String name, String email, String reviewText) {
		
		reviewerName.sendKeys(name);
		reviewerEmail.sendKeys(email);
		reviewerText.sendKeys(reviewText);
		
		submitReviewButton.click();
		
	}
	
	/*
	 * Method to Verify 'Thank you for your review.' text
	 */
	public void verifyReviewSuccessfullySubmittedText() {
		Assert.assertEquals(successAlertMessage.getText(), "Thank you for your review.");
	}
	
	/*
	 * Method to Click 'Add to Cart' on Product 
	 */
	public void clickAddToCartForProduct(String productName) {
		WebElement product = driver.findElement(By.xpath("//p[text()='" + productName + "']/ancestor::div[@class='single-products']"));

        // Hover over the product to trigger the overlay
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();

        // Wait for the "Add to Cart" button to be clickable within the overlay
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='" + productName + "']/ancestor::div[@class='overlay-content']//a[@class='btn btn-default add-to-cart']")));

        // Click the "Add to Cart" button
        addToCartButton.click();
	}
	
	/*
	 * Method to Get All Brand Names
	 */
	public List<String> getBrandNames() {
        List<WebElement> brandElements = driver.findElements(By.xpath("//div[@class='brands_products']//ul[@class='nav nav-pills nav-stacked']/li/a"));
        List<String> brandNames = new ArrayList<>();
        
        for (WebElement brand : brandElements) {
            // Extract brand name text (remove any trailing whitespace or count within parentheses)
            brandNames.add(brand.getText().replaceAll("\\s*\\(\\d+\\)$", "").trim());
            brandNames.add(brand.getText().replaceAll("\\)\\(\\$", "").trim());
        }
        return brandNames;
    }
	
	/*
	 * Method to Verify Brand Names
	 */
	public boolean verifyBrandNames(List<String> expectedBrands) {
        List<String> actualBrands = getBrandNames();
        return actualBrands.equals(expectedBrands);
    }
	
	/*
	 * Method to Click brand by Brand Name
	 */
	public void clickBrand(String brand) {
		String allBrandXpath = "//a[@href = '/brand_products/BRANDNAME']";
		String brandNameXpath = allBrandXpath.replace("BRANDNAME", brand);	
	        
	    WebElement brandNameXpathElement = driver.findElement(By.xpath(brandNameXpath));
	    brandNameXpathElement.click();
		
	}
	
	/*
	 * Method to Verify Products by brand name
	 */
	public void verifyBrandSpecificProductsText(String brandName) {
		Assert.assertEquals(brandSpecificProductsText.getText(), "BRAND -  " + brandName + " PRODUCTS");
	}
	
	/*
	 * Method to Verify Products by Category
	 */
	public void verifyProductCategoryHeader(String expectedText) {
		String actualText = productCategoryHeadingText.getText().replaceAll("\\s+", " ").trim(); // Clean up any extra spaces

	    Assert.assertEquals(actualText, expectedText, "Category text verification failed!");
	}
	
	/*
	 * Method to Enter Product Quantity
	 */
	public void enterProductQuantity(String quantity) {
		productQuantity.clear();
		productQuantity.sendKeys(quantity);
	}
	
	/*
	 * Method to Click Add to Cart
	 */
	public void clickAddToCart() {
		addToCartButton.click();
	}
	
	/*
	 * Method to Click View Cart
	 */
	public void clickViewCart() {
		waitForElementToBeClickable(Duration.ofSeconds(100), viewCartLink);
		viewCartLink.click();
	}
}
