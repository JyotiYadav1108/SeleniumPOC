package com.automation.exercise.pageobjects;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.exercise.TestBase;

public class CheckoutPage extends TestBase{
	
WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static final Logger logger = LogManager.getLogger(CheckoutPage.class.getName());
	
	@FindBy(xpath = "//ul[@id = 'address_delivery']/li[@class = 'address_firstname address_lastname']")
	WebElement customerAddressName;
	
	@FindBy(xpath = "(//ul[@id = 'address_delivery']/li[contains(@class, 'address_address1')])[1]")
	WebElement customerAddress1;
	
	@FindBy(xpath = "(//ul[@id = 'address_delivery']/li[contains(@class, 'address_address1')])[2]")
	WebElement customerAddress2;
	
	@FindBy(xpath = "(//ul[@id = 'address_delivery']/li[contains(@class, 'address_address1')])[3]")
	WebElement customerAddress3;
	
	@FindBy(xpath = "//ul[@id = 'address_delivery']/li[contains(@class, 'address_city')]")
	WebElement customerAddressCity;
	
	@FindBy(xpath = "//ul[@id = 'address_delivery']/li[contains(@class, 'address_country_name')]")
	WebElement customerAddressCountry;
	
	@FindBy(xpath = "//ul[@id = 'address_delivery']/li[contains(@class, 'address_phone')]")
	WebElement customerAddressPhone;
	
	@FindBy(name = "message")
	WebElement descriptionBox;
	
	@FindBy(xpath = "//a[contains(@class, 'check_out')]")
	WebElement placeOrderButton;
	
	@FindBy(xpath = "//div[@id = 'checkoutModal']//u")
	WebElement registerLoginLink;
	
	@FindBy(xpath = "//a[contains(text(), 'Signup / Login')]")
	WebElement signUpLoginLink;
	
	/*
	 * Method to verify Address Details on Checkout
	 */
	public void verifyAddressDetails(String customerName, String customerAddress1, String customerAddress2, String customerAddress3, String customerAddressCity, String customerAddressCountry, String customerAddressPhone) {
		Assert.assertEquals(this.customerAddressName.getText(), customerName);
		Assert.assertEquals(this.customerAddress1.getText(), customerAddress1);
		Assert.assertEquals(this.customerAddress2.getText(), customerAddress2);
		Assert.assertEquals(this.customerAddress3.getText(), customerAddress3);
		Assert.assertEquals(this.customerAddressCity.getText(), customerAddressCity);
		Assert.assertEquals(this.customerAddressCountry.getText(), customerAddressCountry);
		Assert.assertEquals(this.customerAddressPhone.getText(), customerAddressPhone);
		
	}
		 
	/*
	 * Method to Click 'Register/Login' on Checkout
	 */
	public void clickRegisterLoginLinkOnCheckout() {
		registerLoginLink.click();
	}
	
	/*
	 * Method to verify products on Checkout
	 */
    public boolean verifyProducts(List<Map<String, String>> expectedProducts) {
        List<WebElement> productRows = driver.findElements(By.xpath("//table[@class='table table-condensed']/tbody/tr[contains(@id, 'product')]"));
        
        if (productRows.size() != expectedProducts.size()) {
            System.out.println("Product count mismatch.");
            return false;
        }

        // Loop through each product row and verify details
        for (int i = 0; i < productRows.size(); i++) {
            WebElement row = productRows.get(i);
            Map<String, String> expectedProduct = expectedProducts.get(i);

            // Get each product detail
            String productName = row.findElement(By.xpath(".//td[@class='cart_description']/h4/a")).getText();
            String category = row.findElement(By.xpath(".//td[@class='cart_description']/p")).getText();
            String price = row.findElement(By.xpath(".//td[@class='cart_price']/p")).getText();
            String quantity = row.findElement(By.xpath(".//td[@class='cart_quantity']/button")).getText();
            String total = row.findElement(By.xpath(".//td[@class='cart_total']/p[@class='cart_total_price']")).getText();

            // Verify each detail against expected values
            if (!productName.equals(expectedProduct.get("name")) ||
                !category.equals(expectedProduct.get("category")) ||
                !price.equals(expectedProduct.get("price")) ||
                !quantity.equals(expectedProduct.get("quantity")) ||
                !total.equals(expectedProduct.get("total"))) {
                System.out.println("Product details mismatch for product: " + productName);
                return false;
            }
        }

        System.out.println("All products verified successfully.");
        return true;
    }
    
    /*
	 * Method to enter Description Box Details on Checkout
	 */
    public void enterDesciptionBoxDetails(String descriptionOrder) {
		descriptionBox.sendKeys(descriptionOrder);
	}
    
    /*
	 * Method to Click on Place Order
	 */
    public void clickPlaceOder() {
    	placeOrderButton.click();
	}
    
    /*
	 * Method to Click 'Signup/Login' on Checkout
	 */
    public void clickSignUpAndLogin() {
		signUpLoginLink.click();
	}
    
    /*
	 * Method to remove product from Cart on Checkout
	 */
    public void deleteProductByName(String productName) {
        String deleteButtonXpath = "//tr[td[@class='cart_description']/h4/a[text()='" + productName + "']]/td[@class='cart_delete']/a";
        
        WebElement deleteButton = driver.findElement(By.xpath(deleteButtonXpath));
        deleteButton.click();
    }
    
    /*
	 * Method to Verify product quantity on Checkout
	 */
    public boolean verifyProductQuantity(String productName, int expectedQuantity) {
    	WebElement quantityButton = driver.findElement(By.xpath("//tr[td[@class='cart_description']//a[text()='" + productName + "']]//td[@class='cart_quantity']/button"));
        
        // Get the quantity as an integer
        int actualQuantity = Integer.parseInt(quantityButton.getText());
        
        // Compare the actual quantity with the expected quantity
        return actualQuantity == expectedQuantity;
    }
    
}
