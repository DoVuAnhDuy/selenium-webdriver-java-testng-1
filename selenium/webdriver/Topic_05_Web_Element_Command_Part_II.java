package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Command_Part_II {
	WebDriver driver;
	
	// Data
	String email = "AutomationFC@gmail.com";
	String username = "Automation FC";
	
	// Action login page
	By loc_txtEmail = By.id("email");
	By loc_txtUsername = By.id("new_username");
	By loc_txtPassword = By.id("new_password");
	By loc_btnSignUp = By.id("create-account");
	By loc_chkReceiveUpdate = By.id("marketing_newsletter");
	By loc_btnCloseCookie = By.xpath("//div[@id='onetrust-close-btn-container']//button");
	
	By loc_txtLowercase = By.xpath("//div[contains(@class,'password-requirements')]//li[contains(@class,'lowercase-char')]");
	By loc_txtUppercase = By.xpath("//div[contains(@class,'password-requirements')]//li[contains(@class,'uppercase-char')]");
	By loc_txtNumber = By.xpath("//div[contains(@class,'password-requirements')]//li[contains(@class,'number-char')]");
	By loc_txtSpecialChar = By.xpath("//div[contains(@class,'password-requirements')]//li[contains(@class,'special-char')]");
	By loc_txtCharMinimum = By.xpath("//div[contains(@class,'password-requirements')]//li[contains(@class,'8-char')]");
	
	@BeforeTest
	public void beforeTest() {
		String filePath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", filePath +"/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_04_Register_Function_At_MailChimp() {
		// Direct to MailChimp
		driver.get("https://login.mailchimp.com/signup/");
		
		// Input data
		sendkeyToElement(loc_txtEmail, email);
		sendkeyToElement(loc_txtUsername, username);
		
		// Verify status when password is only number
		sendkeyToElement(loc_txtPassword, "589123");
		Assert.assertFalse(isElementCompleted(loc_txtLowercase));
		Assert.assertFalse(isElementCompleted(loc_txtUppercase));
		Assert.assertTrue(isElementCompleted(loc_txtNumber));
		Assert.assertFalse(isElementCompleted(loc_txtSpecialChar));
		Assert.assertFalse(isElementCompleted(loc_txtCharMinimum));
		Assert.assertFalse(isElementEnabled(loc_btnSignUp));
		
		// Verify status when password is only lower-case char
		sendkeyToElement(loc_txtPassword, "abcdef");
		Assert.assertTrue(isElementCompleted(loc_txtLowercase));
		Assert.assertFalse(isElementCompleted(loc_txtUppercase));
		Assert.assertFalse(isElementCompleted(loc_txtNumber));
		Assert.assertFalse(isElementCompleted(loc_txtSpecialChar));
		Assert.assertFalse(isElementCompleted(loc_txtCharMinimum));
		Assert.assertFalse(isElementEnabled(loc_btnSignUp));
		
		// Verify status when password is only upper-case char
		sendkeyToElement(loc_txtPassword, "ASVETFH");
		Assert.assertFalse(isElementCompleted(loc_txtLowercase));
		Assert.assertTrue(isElementCompleted(loc_txtUppercase));
		Assert.assertFalse(isElementCompleted(loc_txtNumber));
		Assert.assertFalse(isElementCompleted(loc_txtSpecialChar));
		Assert.assertFalse(isElementCompleted(loc_txtCharMinimum));
		Assert.assertFalse(isElementEnabled(loc_btnSignUp));
		
		// Verify status when password is only special char
		sendkeyToElement(loc_txtPassword, "@@@@");
		Assert.assertFalse(isElementCompleted(loc_txtLowercase));
		Assert.assertFalse(isElementCompleted(loc_txtUppercase));
		Assert.assertFalse(isElementCompleted(loc_txtNumber));
		Assert.assertTrue(isElementCompleted(loc_txtSpecialChar));
		Assert.assertFalse(isElementCompleted(loc_txtCharMinimum));
		Assert.assertFalse(isElementEnabled(loc_btnSignUp));
		
		// Verify status when password is more than 8 char
		sendkeyToElement(loc_txtPassword, "102938476");
		Assert.assertFalse(isElementCompleted(loc_txtLowercase));
		Assert.assertFalse(isElementCompleted(loc_txtUppercase));
		Assert.assertTrue(isElementCompleted(loc_txtNumber));
		Assert.assertFalse(isElementCompleted(loc_txtSpecialChar));
		Assert.assertTrue(isElementCompleted(loc_txtCharMinimum));
		Assert.assertFalse(isElementEnabled(loc_btnSignUp));
		
		// Verify status of newsletter
		clickOnElement(loc_btnCloseCookie);
		clickOnElement(loc_chkReceiveUpdate);
		Assert.assertTrue(isElementSelected(loc_chkReceiveUpdate));
		
		// Verify button
		sendkeyToElement(loc_txtPassword, "P@ssw0rd");
		Assert.assertTrue(isElementCompleted(loc_txtLowercase));
		Assert.assertTrue(isElementCompleted(loc_txtUppercase));
		Assert.assertTrue(isElementCompleted(loc_txtNumber));
		Assert.assertTrue(isElementCompleted(loc_txtSpecialChar));
		Assert.assertTrue(isElementCompleted(loc_txtCharMinimum));
		Assert.assertTrue(isElementEnabled(loc_btnSignUp));
		
	}
	
	public void sendkeyToElement(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickOnElement(By locator) {
		WebElement element = driver.findElement(locator);
		element.click();
	}
	
	public boolean isElementCompleted(By locator) {
		WebElement element = driver.findElement(locator);
		String attCompleted = element.getAttribute("class");
		if (attCompleted.contains("completed")) {
			System.out.println("Element [" + locator + "] is completed.");
			return true;
		} else {
			System.out.println("Element [" + locator + "] is not completed.");
			return false;
		}
	}
	
	public boolean isElementEnabled(By locator) {
		WebElement element = driver.findElement(locator);
		if (element.isEnabled()) {
			System.out.println("Element [" + locator + "] is enabled.");
			return true;
		} else {
			System.out.println("Element [" + locator + "] is disabled.");
			return false;
		}
	}
	
	public boolean isElementSelected(By locator) {
		WebElement element = driver.findElement(locator);
		if(element.isSelected()) {
			System.out.println("Element [" + locator + "] is selected.");
			return true;
		} else {
			System.out.println("Element [" + locator + "] is de-selected.");
			return false;
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
