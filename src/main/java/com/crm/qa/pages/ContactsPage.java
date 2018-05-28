package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	public ContactsPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[@class='datacardtitle' and contains(text(),'Contacts')]")
	WebElement contactsTable;
	
	@FindBy(xpath="//legend[contains(text(),'Contact Information')]")
	WebElement contactsInformationLebel;
	
	@FindBy(xpath="//select[@name='title']")
	WebElement titleField;
	
	@FindBy(name="first_name")
	WebElement firstNameField;
	
	@FindBy(name="surname")
	WebElement lastNameField;
	
	@FindBy(name="client_lookup")
	WebElement companyNameField;
	
	@FindBy(xpath="//form[@id='contactForm']//child::input[@class='button' and @value='Save']")
	WebElement saveButton;
	
	public boolean verifyContactsTablePresent() {
		return contactsTable.isDisplayed();
	}
	
	public boolean verifyContactsInformationPresent() {
		return contactsInformationLebel.isDisplayed();
	}
	
	public boolean selectContactByName(String name) {
		WebElement contactCheck = driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]//"
				+ "parent::td//preceding-sibling::td/input[@type='checkbox']"));
		
		contactCheck.click();
		
		return contactCheck.isSelected();
	}
	
	public void createNewContact(String title, String firstName, String lastName, String company) {
		Select select = new Select(titleField);
		select.selectByValue(title);
		
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		companyNameField.sendKeys(company);
		
		saveButton.click();
	}
}
