package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[@class='headertext' and contains(text(),'Ganesh')]")
	WebElement userLoggedIn;
	
	@FindBy(xpath="//div[@id='navmenu']//following::a[@title='Contacts']")
	WebElement contactsLink;
	
	@FindBy(xpath="//div[@id='navmenu']//following::a[@title='Deals']")
	WebElement dealsLink;
	
	@FindBy(xpath="//div[@id='navmenu']//following::a[@title='Tasks']")
	WebElement tasksLink;
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyUserLoggedIn() {
		return userLoggedIn.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		
		return new TasksPage();
	}
	
	public ContactsPage clickOnContactSubMenu(String itemName) {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		
		WebElement element = driver.findElement(By.xpath("//a[@title='Contacts']//parent::li//child::ul//a[@title='"+ itemName +"']"));
		action.moveToElement(element).click().build().perform();
		
		return new ContactsPage();
	}
}
