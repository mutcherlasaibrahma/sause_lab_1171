package com.atmecs.saucelab.pageObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.atmecs.saucelab.testCases.TC_AddProducts;
import com.atmecs.saucelab.utills.DataProvider;
import com.atmecs.saucelab.utills.PropertyParser;

public class OrderSummaryPage {
	WebDriver driver;
	Properties properties = PropertyParser
			.getProperties("src/test/java/com/atmecs/saucelab/resources/TestData.properties");

	public OrderSummaryPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void verifyOrderDetailsPage() {
		WebElement orderDetailsPageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
		String pageTitle = orderDetailsPageTitle.getText();
		System.out.println("the order details page title is " + pageTitle);

		if (pageTitle.equalsIgnoreCase(properties.getProperty("product_Order_Details_Page_Title"))) {
			Assert.assertTrue(true);

		} else {
			Assert.assertTrue(false);
		}
	}

	public void verifyOrderSummaryDetails() {
		// Verify order details in summary Page
		verifyOrderSummaryItemDetails();

		// Verify total price in summary Page.
		WebElement totalPriceDetails = driver.findElement(By.xpath("//div[@class='summary_total_label']"));
		String totalOrdersPrices = totalPriceDetails.getText();
		String ActualTotalOrdersPrice = totalOrdersPrices.replace("Total: ", "");
		System.out.println("total order price is " + ActualTotalOrdersPrice);

		// Verify total Order Price with Expected Price
		if (ActualTotalOrdersPrice.equalsIgnoreCase(properties.getProperty("product_Total_Items_Price"))) {
			System.out.println("Total Order Price Is Matched With Expected Data");
			Assert.assertTrue(true);

		} else {
			System.out.println("Total Order Price Is Not Matched With Expected Data");
			Assert.assertTrue(false);
		}

	}

	public void verifyOrderSummaryItemDetails() {
		// Getting the actual data from Product Page
		List<WebElement> elementName = driver.findElements(By.xpath("//*[@id=\"checkout_summary_container\"]"));

		List<CartSummary> cartSummaryList = new ArrayList<>();

		for (WebElement element : elementName) {
			List<WebElement> cartSummaryItems = element.findElements(By.className("cart_item"));
			for (WebElement cartSummaryItem : cartSummaryItems) {
				CartSummary cartSummaryItemObj = new CartSummary();

				List<WebElement> names = cartSummaryItem.findElements(By.className("inventory_item_name"));
				cartSummaryItemObj.setName(names.get(0).getText());

				List<WebElement> quantities = cartSummaryItem.findElements(By.className("cart_quantity"));
				cartSummaryItemObj.setQty(Integer.valueOf(quantities.get(0).getText()));

				List<WebElement> prices = cartSummaryItem.findElements(By.className("inventory_item_price"));
				cartSummaryItemObj.setPrice(prices.get(0).getText());

				cartSummaryList.add(cartSummaryItemObj);

			}

		}

		System.out.println("the order Summary page details are " + cartSummaryList);
		// Getting Expected date from JSON
		CartSummary[] v = DataProvider.createObjectFromJsonFile("src/test/resources/cartTestData.json",
				CartSummary[].class);
		List<CartSummary> expectedList = Arrays.asList(v);
		System.out.println("the Expected list hashcode is " + expectedList.hashCode());

		System.out.println("Actual summary cart Items List" + cartSummaryList);
		System.out.println("the Expected cart Items List is " + expectedList);

		boolean isValidCartDetails = expectedList.containsAll(cartSummaryList);
		System.out.println("the valid cart details matched : " + isValidCartDetails);

		if (isValidCartDetails) {
			System.out.println("Validate Order Details Matched Successfully");
			Assert.assertTrue(isValidCartDetails, "Validate Order Details Matched Successfully");
		} else {
			System.out.println("Validate Order Details Not Matched With Expected Data");
			Assert.assertFalse(isValidCartDetails, "Validate Order Details Not Matched With Expected Data");
		}
		
		// Validate Total Items Details
		
		String actualFirstItemPrice = cartSummaryList.get(0).getPrice();
		String actualSecoundItemPrice = cartSummaryList.get(1).getPrice();
		double actualFirstPrice = Double.valueOf(actualFirstItemPrice.replace("$", ""));
		double actualSecoundPrice = Double.valueOf(actualSecoundItemPrice.replace("$", ""));
		double actualItemTotal = actualFirstPrice + actualSecoundPrice;
		
		
		String expectedFirstItemPrice = expectedList.get(0).getPrice();
		String expectedSecoundItemPrice = expectedList.get(1).getPrice();
		double expectedFirstPrice = Double.valueOf(expectedFirstItemPrice.replace("$", ""));
		double expectedSecoundPrice = Double.valueOf(expectedSecoundItemPrice.replace("$", ""));
		double expectedItemTotal = expectedFirstPrice + expectedSecoundPrice;
		
		try {
			Assert.assertEquals(actualItemTotal, expectedItemTotal);
		} catch (AssertionError e) {
		    System.out.println("Expected Item Total is Not Matched Successfully");
		    throw e;
		}
		System.out.println("Expected Item Total is Matched Successfully");
	}

	public void clickOnFinishBtn() {
		WebElement finishButton = driver.findElement(By.xpath("//*[@id=\"finish\"]"));
		boolean isFinishBtnVisible = isFinishButtonVisible();
		if (isFinishBtnVisible) {
			finishButton.click();
		}
	}

	public boolean isFinishButtonVisible() {
		WebElement finishButton = driver.findElement(By.xpath("//*[@id=\"finish\"]"));
		boolean isFinishButtonVisible = finishButton.isEnabled();
		if (isFinishButtonVisible) {
			System.out.println("Finish Button is Visible");
			Assert.assertTrue(isFinishButtonVisible, "Finish Button is Visible");
		} else {
			System.out.println("Finish Button is not Visible");
			Assert.assertFalse(isFinishButtonVisible, "Finish Button is not Visible");
		}
		return isFinishButtonVisible;
	}

}
