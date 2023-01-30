package com.atmecs.saucelab.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.atmecs.saucelab.testCases.TC_LoginTest;

public class Products extends TC_LoginTest {
	
WebDriver ldriver;
    
    public Products(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
    }
	
	 @FindBy(name="add-to-cart-sauce-labs-backpack")
	    WebElement addToCardSauceBackPack;
	 
	 @FindBy(id="add-to-cart-sauce-labs-bike-light")
	    WebElement addToCardSauceBikeLight;
	 
	 @FindBy(className="shopping_cart_link")
	    WebElement shoppingCardLink;
	 
	 public void addBackPackProduct()
		{
		 addToCardSauceBackPack.click();
		}
	 
	 public void addBikeLightProduct()
		{
		 System.out.println("Entered to this light weight");
		 addToCardSauceBikeLight.click();
		}
	 
}
