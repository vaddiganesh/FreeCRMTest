package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "CRMPRO", "Homepage title mismatch");
	}
	
	@Test(priority=2)
	public void verifyUserLoggedInTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyUserLoggedIn(), "Verification of User logged In failed");
		testUtil.switchToDefault();
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
		
		Assert.assertTrue(contactsPage.verifyContactsTablePresent(),"Contact Table is missing");
		testUtil.switchToDefault();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
