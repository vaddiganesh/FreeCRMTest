package com.crm.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//button[contains(text(),Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String userName, String passWord) {
		username.sendKeys(userName);
		password.sendKeys(passWord);
		password.submit();
		
		//loginBtn.click();
		/*WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(element));*/
		
		return new HomePage();
	}
}
