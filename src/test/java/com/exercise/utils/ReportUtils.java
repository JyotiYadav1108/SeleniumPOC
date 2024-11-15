package com.exercise.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import freemarker.template.SimpleDate;

public class ReportUtils {
	private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport(String reportName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportName);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }
    
    public static void startTest(String testName) {
    	test = extent.createTest(testName);
    }
    
    public static void log(String message) {
    	test.log(Status.INFO, message);
    }
    
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
    	File srcFileName = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String path = "screenshots/" + screenshotName + "_" + timestamp + ".png";
        
        try {
            Files.copy(srcFileName.toPath(), Paths.get(path));
            test.addScreenCaptureFromPath(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
        
        public static void endTest() {
            extent.flush();
        }
        
    
	
}