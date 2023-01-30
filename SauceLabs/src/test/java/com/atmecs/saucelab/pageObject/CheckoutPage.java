package com.atmecs.saucelab.pageObject;

import java.sql.Driver;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.atmecs.saucelab.testCases.TC_AddProducts;
import com.atmecs.saucelab.utills.PropertyParser;

public class CheckoutPage extends TC_AddProducts {

	WebDriver driver;
	Properties properties = PropertyParser
			.getProperties("src/test/java/com/atmecs/saucelab/resources/TestData.properties");

	public CheckoutPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(name = "firstName")
	WebElement txtFirstName;

	@FindBy(name = "lastName")
	WebElement txtLastName;

	@FindBy(name = "postalCode")
	WebElement txtPostalCode;

	@FindBy(name = "continue")
	WebElement btnContiune;

	public void setUserName(String uname) {
		txtFirstName.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		txtLastName.sendKeys(pwd);
	}

	public void setPostalCode(String zipcode) {
		txtPostalCode.sendKeys(zipcode);
	}

	public void clickOnContiuneBtn() {
		btnContiune.click();
	}

	public void clickOnCheckoutButton() {
		CheckoutPage page = new CheckoutPage(driver);
		WebElement checkoutButton = driver.findElement(By.xpath("//*[@id=\"checkout\"]"));
		boolean isChekoutBtnVisible = isCheckoutButtonVisible();
		if (isChekoutBtnVisible) {
			checkoutButton.click();
		}
	}

	public boolean isCheckoutButtonVisible() {
		WebElement checkoutButton = driver.findElement(By.xpath("//*[@id=\"checkout\"]"));
		boolean isCheckOutButtonVisible = checkoutButton.isEnabled();
		if (isCheckOutButtonVisible) {
			System.out.println("Checkout Button is Visible");
			Assert.assertTrue(isCheckOutButtonVisible, "Check-out Button is Visible");
		} else {
			System.out.println("Checkout Button is not Visible");
			Assert.assertFalse(isCheckOutButtonVisible, "Check-out Button is not Visible");
		}
		return isCheckOutButtonVisible;
	}

	public void verifyCheckOutDetailsPage() {
		WebElement checkOutPageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
		String pageTitle = checkOutPageTitle.getText();
		System.out.println("the checkout page title is " + pageTitle);

		if (pageTitle.equalsIgnoreCase(properties.getProperty("product_CheckOut_Page_Title"))) {
			Assert.assertTrue(true);

		} else {
			Assert.assertTrue(false);

		}
	}

	public void addCheckoutDetailsAndClickOnContinueBtn() {

		setUserName(properties.getProperty("product_Checkout_FirstName"));
		System.out.println( "FirstName: " + properties.getProperty("product_Checkout_FirstName"));
		
		setPassword(properties.getProperty("product_Checkout_LastName"));
		System.out.println( "LastName: " + properties.getProperty("product_Checkout_LastName"));
		
		setPostalCode(properties.getProperty("product_Checkout_ZipCode"));
		System.out.println( "Zip/Postal Code: " + properties.getProperty("product_Checkout_ZipCode"));
		
		System.out.println("Added User Details successfully");

		clickOnContiuneBtn();
	}

}
