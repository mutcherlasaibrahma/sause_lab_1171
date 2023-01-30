package com.atmecs.saucelab.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.apache.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	
	public static Logger logger;

	@BeforeClass
	public void setup()
	{			
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\saibrahma.mutcherla\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
