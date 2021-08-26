package webdriver;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_06_TextBox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	// Data
	String userID, password;

	String id;
	String name = "Eliza Cunningham";
	String gender = "female";
	String dateOfBith = "12/20/2001";
	String expectedDoB = "2001-12-20";
	String address = "4413\nRue Levy\nMontreal";
	String expectedAddress = "4413 Rue Levy Montreal";
	String city = "Quebec";
	String state = "QC";
	String PIN = "123456";
	String mobile = "0770002222";
	String email;

	String newAddress = "3480\nFront Street\nOntario";
	String newExpectedAddress = "3480 Front Street Ontario";
	String newCity = "Toronto";
	String newState = "ON";
	String newPIN = "654321";
	String newMobile = "0771115555";
	String newEmail;

	// Action Login page
	By loc_txtUserID = By.name("uid");
	By loc_txtPassword = By.name("password");
	By loc_btnLogin = By.name("btnLogin");
	By loc_lnkHere = By.xpath("//a[text()='here']");

	// Action Get Access page
	By loc_txtEmailID = By.name("emailid");
	By loc_lblUserID = By.xpath("//td[contains(text(),'User ID')]/following-sibling::td");
	By loc_lblPassword = By.xpath("//td[contains(text(),'Password')]/following-sibling::td");

	// Action Home page
	By loc_txtWelcome = By.xpath("//marquee[@class='heading3']");
	// Menu bar
	By loc_mnuNewCustomer = By.xpath("//a[text()='New Customer']");
	By loc_mnuEditCustomer = By.xpath("//a[text()='Edit Customer']");

	// Action Customer page
	By loc_txtName = By.name("name");
	By loc_rdoFemale = By.xpath("//input[@value='f']");
	By loc_txtGender = By.name("gender");
	By loc_txtDateOfBirth = By.name("dob");
	By loc_txaAddress = By.name("addr");
	By loc_txtCity = By.name("city");
	By loc_txtState = By.name("state");
	By loc_txtPIN = By.name("pinno");
	By loc_txtMobile = By.name("telephoneno");
	By loc_txtEmail = By.name("emailid");
	By loc_btnSubmit = By.name("sub");

	// Action Success page
	By loc_txtCustomerSuccess = By.className("heading3");
	By loc_txtCustomerID = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By loc_txtCustomerName = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By loc_txtCustomerGender = By.xpath("//td[text()='Gender']/following-sibling::td");
	By loc_txtBirthDate = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By loc_txtCustomerAddress = By.xpath("//td[text()='Address']/following-sibling::td");
	By loc_txtCustomerCity = By.xpath("//td[text()='City']/following-sibling::td");
	By loc_txtCustomerState = By.xpath("//td[text()='State']/following-sibling::td");
	By loc_txtCustomerPIN = By.xpath("//td[text()='Pin']/following-sibling::td");
	By loc_txtCustomerMobile = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By loc_txtCustomerEmail = By.xpath("//td[text()='Email']/following-sibling::td");

	// Action Edit Customer page
	By loc_txtEditCustomerID = By.name("cusid");
	By loc_btnEditSubmit = By.name("AccSubmit");

	@BeforeTest
	public void beforeTest() {
		String filePath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", filePath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String date = Long.toString(timestamp.getTime());
		email = "Auto_" + date + "@gmail.com";
		newEmail = "Auto_" + date + "@gmail.com";
	}

	@Test
	public void TC_01_Hanle_On_TextBox_TextArea() {
		String loginPageURL = driver.getCurrentUrl();

		// Click on Here
		clickOnElement(loc_lnkHere);

		// Enter email & submit
		sendkeyToElement(loc_txtEmailID, email);
		clickOnElement(loc_btnLogin);

		// Get UserID & Password
		userID = getTextFromElement(loc_lblUserID);
		password = getTextFromElement(loc_lblPassword);

		// Back to Login page
		driver.get(loginPageURL);

		// Login with created access
		sendkeyToElement(loc_txtUserID, userID);
		sendkeyToElement(loc_txtPassword, password);
		clickOnElement(loc_btnLogin);

		// Verify Home page
		Assert.assertEquals(getTextFromElement(loc_txtWelcome), "Welcome To Manager's Page of Guru99 Bank");

		// Click on New Customer
		clickOnElement(loc_mnuNewCustomer);

		// Remove date format
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(loc_txtDateOfBirth));

		// Input data
		sendkeyToElement(loc_txtName, name);
		clickOnElement(loc_rdoFemale);
		sendkeyToElement(loc_txtDateOfBirth, dateOfBith);
		sendkeyToElement(loc_txaAddress, address);
		sendkeyToElement(loc_txtCity, city);
		sendkeyToElement(loc_txtState, state);
		sendkeyToElement(loc_txtPIN, PIN);
		sendkeyToElement(loc_txtMobile, mobile);
		sendkeyToElement(loc_txtEmail, email);
		sendkeyToElement(loc_txtPassword, password);
		clickOnElement(loc_btnSubmit);

		// Verify Success page
		Assert.assertEquals(getTextFromElement(loc_txtCustomerSuccess), "Customer Registered Successfully!!!");

		id = getTextFromElement(loc_txtCustomerID);

		// Verify Register data
		Assert.assertEquals(getTextFromElement(loc_txtCustomerName), name);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerGender), gender);
		Assert.assertEquals(getTextFromElement(loc_txtBirthDate), expectedDoB);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerAddress), expectedAddress);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerCity), city);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerState), state);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerPIN), PIN);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerMobile), mobile);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerEmail), email);

		// Click on Edit customer
		clickOnElement(loc_mnuEditCustomer);

		// Input data
		sendkeyToElement(loc_txtEditCustomerID, id);
		clickOnElement(loc_btnEditSubmit);

		// Verify Edit data
		Assert.assertEquals(getTextFromAttribute(loc_txtName), name);
		Assert.assertEquals(getTextFromAttribute(loc_txtGender), gender);
		Assert.assertEquals(getTextFromAttribute(loc_txtDateOfBirth), expectedDoB);
		Assert.assertEquals(getTextFromElement(loc_txaAddress), address);
		Assert.assertEquals(getTextFromAttribute(loc_txtCity), city);
		Assert.assertEquals(getTextFromAttribute(loc_txtState), state);
		Assert.assertEquals(getTextFromAttribute(loc_txtPIN), PIN);
		Assert.assertEquals(getTextFromAttribute(loc_txtMobile), mobile);
		Assert.assertEquals(getTextFromAttribute(loc_txtEmail), email);

		// Edit data
		sendkeyToElement(loc_txaAddress, newAddress);
		sendkeyToElement(loc_txtCity, newCity);
		sendkeyToElement(loc_txtState, newState);
		sendkeyToElement(loc_txtPIN, newPIN);
		sendkeyToElement(loc_txtMobile, newMobile);
		sendkeyToElement(loc_txtEmail, newEmail);
		clickOnElement(loc_btnSubmit);

		// Verify Success page
		Assert.assertEquals(getTextFromElement(loc_txtCustomerSuccess), "Customer details updated Successfully!!!");

		// Verify Register data
		Assert.assertEquals(getTextFromElement(loc_txtCustomerID), id);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerName), name);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerGender), gender);
		Assert.assertEquals(getTextFromElement(loc_txtBirthDate), expectedDoB);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerAddress), newExpectedAddress);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerCity), newCity);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerState), newState);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerPIN), newPIN);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerMobile), newMobile);
		Assert.assertEquals(getTextFromElement(loc_txtCustomerEmail), newEmail);
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

	public String getTextFromElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element.getText();
	}

	public String getTextFromAttribute(By locator) {
		WebElement element = driver.findElement(locator);
		return element.getAttribute("value");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
