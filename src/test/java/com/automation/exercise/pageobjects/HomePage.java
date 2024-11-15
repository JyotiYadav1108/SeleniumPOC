package com.automation.exercise.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.exercise.TestBase;

public class HomePage extends TestBase {
	
	WebDriver driver;
	public static final Logger logger = LogManager.getLogger(HomePage.class.getName());
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@href = '/' and @style = 'color: orange;']")
	WebElement homePageLink;
	
	@FindBy(xpath = "//a[@href = '/login']")
	WebElement signUpLoginLink;
	
	@FindBy(xpath = "//a[@href='/contact_us']")
	WebElement contactUs;
	
	@FindBy(xpath = "//a[@href='/view_cart']")
	WebElement cart;
	
	@FindBy(xpath = "//a[@href='/test_cases']")
	WebElement testCases;
	
	@FindBy(xpath = "//a[@href='/products']")
	WebElement products;
	
	@FindBy(xpath = "//a[@href='/logout']")
	WebElement logOut;
	
	@FindBy(xpath = "//div[@class = 'single-widget']/h2")
	WebElement subscriptionText;
	
	@FindBy(xpath = "//input[@id = 'susbscribe_email']")
	WebElement subscriptionEmail;
	
	@FindBy(xpath = "//button[@id = 'subscribe']")
	WebElement subscriptionSubmit;
	
	@FindBy(xpath = "//div[@class = 'alert-success alert']")
	WebElement subscriptionSuccessful;
		
	@FindBy(xpath = "//div[@class='carousel-inner']/div[contains(@class, 'item active')]//h2[text()='Full-Fledged practice website for Automation Engineers']")
	WebElement pageHeader;
	
	@FindBy(xpath = "//a[@id='scrollUp']/i[@class='fa fa-angle-up']")
	WebElement scrollUpArrow;
	
	@FindBy(xpath = "//div[@class = 'recommended_items']/h2")
	WebElement recommendedItemsText;
	
	@FindBy(xpath = "(//div[@class = 'recommended_items']//div[contains(@class, 'carousel-inner')]//div[contains(@class, 'item') and contains(@class, 'active')]//a[contains(@class, 'add-to-cart')])[1]")
	WebElement recommendedItemsAddToCart;
	
	@FindBy(xpath = "//div[@class='panel-heading']//a[@href='#CATEGORY']/../../following-sibling::div//a[contains(text(), 'SUBCATEGORY')]")
	WebElement subCategory;

	
	/*
	 * Method to Click 'SignUp/Login' on Home Page
	 */
	public void clickSignUpLoginLink() {
		
		signUpLoginLink.click();
	}
	
	/*
	 * Method to Click Contact Us on Home Page
	 */
	public void contactUsClick() {
		logger.info("Clicking on Contact Us link from Home Page");
		contactUs.click();
	}
	
	/*
	 * Method to Click Cart on Home Page
	 */
	public void cartClick() {
		logger.info("Clicking on Cart link from Home Page");
		cart.click();
	}
	
	/*
	 * Method to Click Products Link on Home Page
	 */
	public void productClick() {
		logger.info("Clicking on Product link from Home Page");
		products.click();
	}
	
	/*
	 * Method to Click Logout on Home Page
	 */
	public void logoutClick() {
		logOut.click();
	}
	
	/*
	 * Method to Click Test Cases on Home Page
	 */
	public void testCasesClick() {
		logger.info("Clicking on Test Cases link from Home Page");
		testCases.click();
	}
	
	/*
	 * Method to Verify Home Page Title
	 */
	public void verifyHomePageTitle() {
		logger.info("Verify the Home Page Title");
		Assert.assertEquals(driver.getTitle(), "Automation Exercise");
	}
	
	/*
	 * Method to Verify Subscription Text
	 */
	public void verifySubsciptionTextOnHomePage() {
		scrollDownToSpecificElement(subscriptionText);
		Assert.assertEquals(subscriptionText.getText(), "SUBSCRIPTION");
	}
	
	/*
	 * Method to Enter email in Subscription Box
	 */
	public void fillDetailsInSubscriptionBox(String email) {
		subscriptionEmail.sendKeys(email);
		subscriptionSubmit.click();
	}
	
	/*
	 * Method to Verify successful message after subscribing on Home Page
	 */
	public void verifySuccessMessageAfterSubscribing() {
		Assert.assertEquals(subscriptionSuccessful.getText(), "You have been successfully subscribed!");
	}
	
	/*
	 * Method to Verify Page Header on Home Page
	 */
	public void verifyPageHeaderForAutomationExercise() {
		scrollDownToSpecificElement(pageHeader);
		Assert.assertEquals(pageHeader.getText(), "Full-Fledged practice website for Automation Engineers");
	}
	
	/*
	 * Method to click on Scroll Up Arrow 
	 */
	public void clickScrollUpArrow() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollUpArrow);
		scrollUpArrow.click();
	}

	/*
	 * Method to Verify recommended Items 
	 */
	public void verifyRecommendedItemsIsVisible() {
		Assert.assertTrue(recommendedItemsText.isDisplayed(), "Verified that recommended items text is visible");
	}
	
	/*
	 * Method to add items to cart from recommended items
	 */
	public void clickAddToCardFromRecommendedItems() {
		recommendedItemsAddToCart.click();
	}
	
	/*
	 * Method to Click 'View Product' 
	 */
	public void clickOnViewProduct(String productName) {
		//String baseXPath = "//div[@class = 'features_items']//div[@class = 'col-sm-4']//div[@class = 'productinfo text-center']/p[contains(text(), 'PRODUCTNAME')]/ancestor::div[@class = 'single-products']/following-sibling::*//a";
		String baseXPath = "//div[@class = 'productinfo text-center']/p[contains(text(), 'PRODUCTNAME')]/ancestor::div[@class = 'single-products']/following-sibling::*//a";
		String xpathBasedOnProductName = baseXPath.replace("PRODUCTNAME", productName);
		WebElement viewProductButton = driver.findElement(By.xpath(xpathBasedOnProductName));
		viewProductButton.click();
	}

	/*
	 * Method to get all categories from Products
	 */
    public List<String> getCategoryNames() {
        List<WebElement> categoryElements = driver.findElements(By.xpath("//div[@class='panel-group category-products']//div[@class='panel-heading']/h4[@class='panel-title']/a"));
        List<String> categoryNames = new ArrayList<>();
        
        for (WebElement category : categoryElements) {
            categoryNames.add(category.getText().trim());
        }
        return categoryNames;
    }

    /*
	 * Method to Verify the product categories
	 */
    public boolean verifyCategoryNames(List<String> expectedCategories) {
        List<String> actualCategories = getCategoryNames();
        return actualCategories.equals(expectedCategories);
    }
    
    /*
	 * Method to click the product category - Example: Men, Women, Kids
	 */
    public void clickCategory(String category) {
    	
    	String categoryXpath = "//div[@class='panel-group category-products']//div[@class='panel-heading']/h4[@class='panel-title']//a[@href = '#CATEGORY']";
    	WebElement categoryElement = driver.findElement(By.xpath(categoryXpath.replace("CATEGORY", category)));
    	
    	categoryElement.click();
    }
    
    /*
	 * Method to click the product subcategory - Example: Dress, Shirts
	 */
    public void clickSubCategoryProduct(String category, String subCategory) {
    	String subCategoryXpath = "//div[@class='panel-heading']//a[@href='#CATEGORY']/../../following-sibling::div//a[contains(text(), 'SUB')]";
    	WebElement subCategoryElement = driver.findElement(By.xpath(subCategoryXpath.replace("CATEGORY", category).replace("SUB", subCategory)));
    	
    	subCategoryElement.click();
    }
	
    /*
	 * Method to add items to cart from recommended items
	 */
    public void addToCartByProductNameRecommendedItems(String productName) {
        // Locate all active items in the carousel using XPath
        List<WebElement> activeItems = driver.findElements(By.xpath("//div[@class = 'recommended_items']//div[contains(@class, 'carousel-inner')]//div[contains(@class, 'item') and contains(@class, 'active')]"));

        // Iterate through active items to find the specified product
        for (WebElement item : activeItems) {
            WebElement nameElement = item.findElement(By.xpath("//div[@class = 'recommended_items']//div[@class='productinfo text-center']/p"));
            String name = nameElement.getText().trim();

            // Check if the product name matches the specified name
            if (name.equalsIgnoreCase(productName)) {
                // Find and click the "Add to cart" button for this product
                WebElement addToCartButton = item.findElement(By.xpath("//div[@class = 'recommended_items']//a[contains(@class, 'add-to-cart')]"));
                addToCartButton.click();
                return; // Exit once the desired product is added to cart
            }
        }
       

        // Log or throw an exception if the product is not found
        throw new RuntimeException("Product with name '" + productName + "' not found in the active carousel.");
    }
	
}
