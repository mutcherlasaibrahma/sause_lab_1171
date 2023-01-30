package com.atmecs.saucelab.testCases;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.saucelab.pageObject.LoginPage;
import com.atmecs.saucelab.utills.PropertyParser;

public class TC_LoginTest extends BaseClass {
	@Test
	public void loginTest() throws InterruptedException {
		System.out.println("welcome to login test ");

		Properties properties = PropertyParser
				.getProperties("src/test/java/com/atmecs/saucelab/resources/TestData.properties");

		LoginPage loginpage = new LoginPage(driver);

		// logger.info("Application Launched");
		driver.get("https://www.saucedemo.com/");

		// logger.info("Entered username");
		loginpage.setUserName(properties.getProperty("username"));

		loginpage.setPassword(properties.getProperty("password"));
		// logger.info("Entered password");

		loginpage.clickLogin();
		System.out.println("the current url is " + driver.getCurrentUrl());

		// Thread.sleep(10000);

		if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"))

		{
			Assert.assertTrue(true);
			System.out.println("Login test passed");
		} else {
			Assert.assertTrue(false);
			System.out.println("Login test failed");
		}

	}
}
