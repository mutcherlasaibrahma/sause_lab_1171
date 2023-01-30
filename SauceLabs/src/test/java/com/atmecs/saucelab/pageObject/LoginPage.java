package com.atmecs.saucelab.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.atmecs.saucelab.testCases.BaseClass;

/**
 * This class used for locator for login page.
 * 
 * @author Saibrahma.Mutcherla
 *
 */
public class LoginPage {
  
	WebDriver ldriver;
    
    public LoginPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
    }
        
    @FindBy(name="user-name")
    WebElement txtUserName;
    
    @FindBy(name="password")
    WebElement txtPassword;
    
    @FindBy(name="login-button")
    WebElement btnLogin;
     
    
    public void setUserName(String uname)
    {
        txtUserName.sendKeys(uname);
    }
    
    public void setPassword(String pwd)
    {
        txtPassword.sendKeys(pwd);
    }
    
    
    public void clickLogin()
    {
        btnLogin.click();
    }
    
    
    
}