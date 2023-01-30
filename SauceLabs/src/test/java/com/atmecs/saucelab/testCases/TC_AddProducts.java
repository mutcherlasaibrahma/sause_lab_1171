package com.atmecs.saucelab.testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.saucelab.pageObject.CartItem;
import com.atmecs.saucelab.pageObject.CheckoutPage;
import com.atmecs.saucelab.pageObject.OrderSummaryPage;
import com.atmecs.saucelab.pageObject.Products;
import com.atmecs.saucelab.utills.DataProvider;
import com.atmecs.saucelab.utills.PropertyParser;

public class TC_AddProducts extends BaseClass {

	Properties properties = PropertyParser
			.getProperties("src/test/java/com/atmecs/saucelab/resources/TestData.properties");

	@Test
	public void addProducts() throws InterruptedException {
		Products products = new Products(driver);
		// Reusing Login Test in the Add products Place
		products.loginTest();

		// Adding the products to cart
		addProductsToCard();

		// Click on Cart Button
		clickOnCartDetailsbtn();

		// Verifying the title for the Cart Details Page
		verifyCartDetailsPage();

		// Verify order Details 
		verifyOrderDetails();

		//// Click on Checkout Button
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.clickOnCheckoutButton();
		
		//Verify checkout checkout  details page
		checkoutPage.verifyCheckOutDetailsPage();
		
		//Verify and click on Continue Btn
		checkoutPage.addCheckoutDetailsAndClickOnContinueBtn();
		
		//Verify OrderSummary Page
		OrderSummaryPage orderSummaryPage = new OrderSummaryPage(driver);
		orderSummaryPage.verifyOrderDetailsPage();
		
		//Verify OrderSummary Details
		orderSummaryPage.verifyOrderSummaryDetails();
		
		// Click on Finish Button
		orderSummaryPage.clickOnFinishBtn();
	}

	public void addProductsToCard() {
		Products products = new Products(driver);
		products.addBikeLightProduct();
		products.addBackPackProduct();
	}

	public void clickOnCartDetailsbtn() {
		WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
		cartButton.click();
	}

	public void verifyCartDetailsPage() {
		WebElement cartPageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
		String pageTitle = cartPageTitle.getText();

		if (pageTitle.equalsIgnoreCase(properties.getProperty("product_Cart_Page_Title"))) {
			Assert.assertTrue(true);

		} else {
			Assert.assertTrue(false);

		}
	}
	
	public void verifyOrderDetails() {
		// Getting the actual data from Product Page
		List<WebElement> elementName = driver.findElements(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]"));

		List<CartItem> cartItemList = new ArrayList<>();

		for (WebElement element : elementName) {
			List<WebElement> cartItems = element.findElements(By.className("cart_item"));
			for (WebElement cartItem : cartItems) {
				CartItem cartItemObj = new CartItem();
				
				List<WebElement> names = cartItem.findElements(By.className("inventory_item_name"));
				cartItemObj.setName(names.get(0).getText());

				List<WebElement> quantities = cartItem.findElements(By.className("cart_quantity"));
				cartItemObj.setQty(Integer.valueOf(quantities.get(0).getText()));

				List<WebElement> prices = cartItem.findElements(By.className("inventory_item_price"));
				cartItemObj.setPrice(prices.get(0).getText());

				cartItemList.add(cartItemObj);

			}

		}

		List<CartItem> actualCartList = cartItemList;
		System.out.println("Actual cart items list" + cartItemList.hashCode());

		// Getting Expected date from JSON
		CartItem[] v = DataProvider.createObjectFromJsonFile("src/test/resources/cartTestData.json", CartItem[].class);
		List<CartItem> expectedList = Arrays.asList(v);
		System.out.println("the Expected list hashcode is " + expectedList.hashCode());

		System.out.println("Actual cart Items List" + actualCartList);
		System.out.println("the Expected cart Items List is " + expectedList);

		boolean isValidCartDetails = expectedList.containsAll(actualCartList);
		System.out.println("the valid cart details matched : " + isValidCartDetails);

		if (isValidCartDetails) {
			System.out.println("Validate Order Details Matched Successfully");
			Assert.assertTrue(isValidCartDetails, "Validate Order Details Matched Successfully");
		} else {
			System.out.println("Validate Order Details Not Matched With Expected Data");
			Assert.assertFalse(isValidCartDetails, "Validate Order Details Not Matched With Expected Data");
		}
	}

}
