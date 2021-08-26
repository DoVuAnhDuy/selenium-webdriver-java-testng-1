package webdriver;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_Xpath_CSS_Part_II {
	WebDriver driver;

	// Action Account Wrapper
	By loc_lnkAccount = By.xpath("//div[@class='account-cart-wrapper']//a[contains(.,'Account')]");
	By loc_lnkMyAccount = By.xpath("//div[@id='header-account']//a[@title='My Account']");
	By loc_lnkLogout = By.xpath("//div[@id='header-account']//a[@title='Log Out']");

	// Action Home page
	By loc_txtTitle = By.className("page-title");
	By loc_lnkFooterAccount = By.xpath("//div[@class='footer']//a[@title='My Account']");

	// Action Login page
	By loc_txtEmail = By.id("email");
	By loc_txtPassword = By.id("pass");
	By loc_btnLogin = By.id("send2");
	By loc_btnCreateAccount = By.xpath("//a[@title='Create an Account']");

	// Action Register page
	By loc_txtFirstName = By.id("firstname");
	By loc_txtLastName = By.id("lastname");
	By loc_txtRegisterEmail = By.id("email_address");
	By loc_txtRegisterPassword = By.id("password");
	By loc_txtConfirmPassword = By.id("confirmation");
	By loc_btnRegister = By.xpath("//button[@title='Register']");

	// Action Account page
	By loc_lblDashboard = By.className("page-title");
	By loc_txtContactInformation = By.xpath(
			"//div[contains(@class,'box-title') and contains(.,'Contact Information')]/following-sibling::div/p");

	// Error
	By loc_txtEmailError = By.xpath("//input[@id='email']/following-sibling::div");
	By loc_txtPasswordError = By.xpath("//input[@id='pass']/following-sibling::div");
	By loc_txtMessageError = By.xpath("//li[@class='error-msg']//span");
	By loc_txtMessageSuccess = By.xpath("//li[@class='success-msg']//span");
	By loc_txtMessageHello = By.className("hello");

	// Data
	String firstName = "Automation";
	String lastName = "FC";
	String email = "automationfc@gmail.com";
	String password = "123456";

	@BeforeTest
	public void beforeTest() {
		String filePath = System.getProperty("user.dir");;
		System.setProperty("webdriver.gecko.driver", filePath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Login_With_Empty_Data() {
		// Click on Account link
		driver.findElement(loc_lnkFooterAccount).click();

		// Click on Login button
		driver.findElement(loc_btnLogin).click();

		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtEmailError).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(loc_txtPasswordError).getText(), "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		// Click on Account link
		driver.findElement(loc_lnkFooterAccount).click();

		// Input data
		driver.findElement(loc_txtEmail).sendKeys("Automation@email");
		driver.findElement(loc_txtPassword).sendKeys(password);

		// Click on Login button
		driver.findElement(loc_btnLogin).click();

		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtEmailError).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_With_Password_Lower_6_Characters() {
		// Click on Account link
		driver.findElement(loc_lnkFooterAccount).click();

		// Input data
		driver.findElement(loc_txtEmail).sendKeys(email);
		driver.findElement(loc_txtPassword).sendKeys("3253");

		// Click on Login button
		driver.findElement(loc_btnLogin).click();

		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtPasswordError).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Login_With_Incorrect_Email_Or_Password() {
		// Click on Account link
		driver.findElement(loc_lnkFooterAccount).click();

		// Input data
		driver.findElement(loc_txtEmail).sendKeys("automationTest@gmail.com");
		driver.findElement(loc_txtPassword).sendKeys("112345784");

		// Click on Login button
		driver.findElement(loc_btnLogin).click();

		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtMessageError).getText(), "Invalid login or password.");
	}

	@Test
	public void TC_05_Create_New_Account() throws InterruptedException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Click on Account link
		driver.findElement(loc_lnkFooterAccount).click();

		// Click on Create an Account button
		driver.findElement(loc_btnCreateAccount).click();

		// Input data
		String date = Long.toString(timestamp.getTime());
		email = firstName + lastName + date + "@gmail.com";
		driver.findElement(loc_txtFirstName).sendKeys(firstName);
		driver.findElement(loc_txtLastName).sendKeys(lastName);
		driver.findElement(loc_txtRegisterEmail).sendKeys(email);
		driver.findElement(loc_txtRegisterPassword).sendKeys(password);
		driver.findElement(loc_txtConfirmPassword).sendKeys(password);

		// Click on Register button
		driver.findElement(loc_btnRegister).click();

		// Verify message
		Assert.assertEquals(driver.findElement(loc_lblDashboard).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(loc_txtMessageSuccess).getText(),
				"Thank you for registering with Main Website Store.");
		Assert.assertTrue(driver.findElement(loc_txtMessageHello).getText().contains(firstName + " " + lastName));
		Assert.assertTrue(driver.findElement(loc_txtContactInformation).getText().contains(firstName + " " + lastName));
		Assert.assertTrue(driver.findElement(loc_txtContactInformation).getText().contains(email));

		// Logout
		driver.findElement(loc_lnkAccount).click();
		driver.findElement(loc_lnkLogout).click();
		
		Thread.sleep(7000);

		// Verify homepage
		System.out.println(driver.findElement(loc_txtTitle).getText());
		Assert.assertTrue(driver.findElement(loc_txtTitle).getText().contains("THIS IS DEMO SITE"));
	}

	@Test
	public void TC_06_Login_With_Valid_Email() {
		// Click on Account link
		driver.findElement(loc_lnkFooterAccount).click();

		// Login as created account
		driver.findElement(loc_txtEmail).sendKeys(email);
		driver.findElement(loc_txtPassword).sendKeys(password);
		driver.findElement(loc_btnLogin).click();

		// Verify message
		Assert.assertEquals(driver.findElement(loc_lblDashboard).getText(), "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(loc_txtMessageHello).getText().contains(firstName + " " + lastName));
		Assert.assertTrue(driver.findElement(loc_txtContactInformation).getText().contains(firstName + " " + lastName));
		Assert.assertTrue(driver.findElement(loc_txtContactInformation).getText().contains(email));

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
