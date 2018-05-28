package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("F:\\Ganesh\\SeleniumWork\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("disable-infobars");
			
			System.setProperty("webdriver.chrome.driver", "F:\\Ganesh\\Softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(options);
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "F:\\Ganesh\\Softwares\\geckodriver-v0.20.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equals("ie")) {
		/*	DesiredCapabilities caps = DesiredCapabilities.internetExplorer(); 
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); */
			
			System.setProperty("webdriver.ie.driver", "F:\\Ganesh\\Softwares\\IEDriverServer_Win32_3.8.0\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		//Config to write webdriver event logs configured in WebEventListener class
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
}
