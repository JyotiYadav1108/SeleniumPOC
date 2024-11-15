package com.automation.exercise.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestCasesPage {
	
	WebDriver driver;
	public static final Logger logger = LogManager.getLogger(TestCasesPage.class.getName());
	
	public TestCasesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Method to Verify Test Cases Page Title
	 */
	public void verifyTestCasesPageTitle() {
		Assert.assertEquals(driver.getTitle(), "Automation Practice Website for UI Testing - Test Cases");
	}

}
