package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "ContactsPage";
	
	public ContactsPageTest() {
		super(); //to call super class constructor
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = new HomePage();
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactCheckboxSelectionByNameTest() {
		Assert.assertTrue(contactsPage.selectContactByName("Dinesh Vaddi"), "Contact not selected properly");
	}
	
	@Test(priority=2, dataProvider="getContactsTestData",enabled=false)
	public void createNewContactTest(String title, String ftName, String ltName, String cmp) {
		homePage.clickOnContactSubMenu("New Contact");
		contactsPage.createNewContact(title, ftName, ltName, cmp);
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] testData = null;
		testData = TestUtil.getTestData(sheetName);
		return testData;
	}
	
	
	@AfterMethod
	public void tearDown() {
		testUtil.switchToDefault();
		driver.quit();
	}
}
