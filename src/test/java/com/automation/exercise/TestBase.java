package com.automation.exercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
 
import org.testng.annotations.BeforeSuite;

import com.exercise.utils.ExcelUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	// For testing GIT
	public static final Logger logger=LogManager.getLogger(TestBase.class);
	public static WebDriver driver;
	public Properties prop = new Properties();
	
	String currentWorkingDirectory;
	
	String reportFileName;
	
	
	String testDataFile;
	ExcelUtils excelUtils;
	
	String downloadFilePath = System.getProperty("user.home") + "/Downloads";
	
	@BeforeSuite
	public void PreSetup() {
		currentWorkingDirectory = System.getProperty("user.dir");
		
		reportFileName = currentWorkingDirectory + "/reports/exerciseReports.html";
		
	}
	
	/*
	 * Method to Get Driver Instance
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
	/*
	 * Method to Select Browser
	 */
	public void selectBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			logger.info("Launching browser ----- " + browser);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();			
		}
		else
			if(browser.equalsIgnoreCase("edge"))
			{
				logger.info("Launching browser ----- " + browser);
				driver = new EdgeDriver();
				driver.manage().window().maximize();
			}
	}
	
	/*
	 * Method to Navigate to URL
	 */
	public void getURL(String URL) {
		logger.info("Navigating to URL  ----- " + URL);
		driver.get(URL);
	}
	
	/*
	 * Method to Load Config Details
	 */
	public void loadConfigData() throws IOException {
		File config = new File(System.getProperty("user.dir") + "//config//config.properties");
		logger.info("----- Loading Config Data ----- ");
		
		FileInputStream fs = new FileInputStream(config);
	
		prop.load(fs);
		

	}
	
	/*
	 * Method to load config details, select browser and navigate to Base URL
	 */
	public void init() throws IOException {
		loadConfigData();
		selectBrowser(prop.getProperty("browser"));
		getURL(prop.getProperty("url"));
	}
	
	/*
	 * Method to Close Driver Instance
	 */
	public void closeBrowser() {
		driver.close();
	}
	
	/*
	 * Method to Quit Driver Instance
	 */
	public void quiteBrowser()
	{
		driver.quit();
	}

	/*
	 * Method to Implement Implicit Wait
	 */
	public void hardWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	/*
	 * Method to Implement Wait till Element is Visible
	 */
	public void waitForElementVisible(Duration time, By ele) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));	
	}
	
	/*
	 * Method to Implement Wait till Element is Clickable
	 */
	public void waitForElementToBeClickable(Duration time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	/*
	 * Method to Select Dropdown Value
	 */
	public void selectDropdownByValue(WebElement ele, String text) {
		Select select = new Select(ele);
		select.selectByValue(text);
	}
	
	/*
	 * Method to Verify Page Title
	 */
	public void verifyPageTitle(String expectedPageTitle) {
		Assert.assertEquals(driver.getTitle(), expectedPageTitle, "Verified that page title equals expected page title");
	}
	
	/*
	 * Method to Get Data from Excel file as per Key 
	 */
	public String getData(String key) throws IOException {
		testDataFile = System.getProperty("user.dir") + "\\src\\main\\resources\\com\\exercise\\testdata\\TestData.xlsx";
		Map<String, String> excelData = excelUtils.readExcelData(testDataFile);
		
		String data = excelData.get(key);
		return data;
		
	}
	
	/*
	 * Method to Scroll Down on WebPage
	 */
	public void scrollDownOnWebPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}
	
	/*
	 * Method to Scroll Up on WebPage
	 */
	public void scrollUpOnWebPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");

	}
	
	/*
	 * Method to Scroll Down to Specific Element
	 */
	public void scrollDownToSpecificElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/*
	 * Method to implement Mouse Hover and Click on WebElement
	 */
	public void mouseHoverAndClick(WebElement element) {
		Actions actions=new Actions(driver);
		actions.moveToElement(element).perform();
		actions.click(element).perform();
	}
	
	/*
	 * Method to Check if file is downloaded
	 */
	public boolean isFileDownloaded(String fileName) {
        File file = new File(downloadFilePath + "/" + fileName);
        int attempts = 0;
        while (attempts < 5) {  // Check periodically if file download is complete
            if (file.exists()) {
                System.out.println("File found: " + fileName);
                return true;
            }
            attempts++;
            try {
                Thread.sleep(1000);  // Wait for a second before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("File not found: " + fileName);
        return false;
    }
	
	/*
	 * Method to Verify if URL comtains string
	 */
	public void verifyUrlContains(String partOfUrl, String msg) { 
		  Assert.assertEquals(driver.getCurrentUrl().contains(partOfUrl), true, msg);
	}
	
}

