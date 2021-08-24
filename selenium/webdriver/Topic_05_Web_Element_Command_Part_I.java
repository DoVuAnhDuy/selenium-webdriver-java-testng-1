package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Command_Part_I {
	WebDriver driver;

	// Action
	By loc_txtEmail = By.id("mail");
	By loc_rdoAgeUnder18 = By.id("under_18");
	By loc_rdoAgeOver18 = By.id("over_18");
	By loc_txaEducation = By.id("edu");
	By loc_txtUser5 = By.xpath("//h5[text()='Name: User5']");
	By loc_ddlJobRole01 = By.id("job1");
	By loc_ddlJobRole02 = By.id("job2");
	By loc_chkDevelopment = By.id("development");
	By loc_chkJava = By.id("java");
	By loc_sldSlider01 = By.id("slider-1");

	By loc_txtPassword = By.id("password");
	By loc_rdoAgeDisabled = By.id("radio-disabled");
	By loc_txaBiography = By.id("bio");
	By loc_ddlJobRole03 = By.id("job3");
	By loc_chkDisabled = By.id("check-disbaled");
	By loc_sldSlider02 = By.id("slider-2");

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Verify_Is_Displayed() {
		// Verify Email text box is displayed
		if (isElementDisplayed(loc_txtEmail)) {
			sendKeyToElement(loc_txtEmail, "Automation Testing");
		}

		// Verify Age Under 18 radio button is displayed
		if (isElementDisplayed(loc_rdoAgeUnder18)) {
			clickOnElement(loc_rdoAgeUnder18);
		}

		// Verify Education text area is displayed
		if (isElementDisplayed(loc_txaEducation)) {
			sendKeyToElement(loc_txaEducation, "Automation Testing");
		}

		// Verify Name user is not displayed
		Assert.assertFalse(isElementDisplayed(loc_txtUser5));
	}

	@Test
	public void TC_02_Verify_Is_Enabled() {
		// Verify element is enabled
		isElementEnabled(loc_txtEmail);
		isElementEnabled(loc_rdoAgeUnder18);
		isElementEnabled(loc_txaEducation);
		isElementEnabled(loc_ddlJobRole01);
		isElementEnabled(loc_ddlJobRole02);
		isElementEnabled(loc_chkDevelopment);
		isElementEnabled(loc_sldSlider01);

		// Verify element is disabled
		isElementEnabled(loc_txtPassword);
		isElementEnabled(loc_rdoAgeDisabled);
		isElementEnabled(loc_txaBiography);
		isElementEnabled(loc_ddlJobRole03);
		isElementEnabled(loc_chkDisabled);
		isElementEnabled(loc_sldSlider02);
	}

	@Test
	public void TC_03_Verify_Is_Selected() {
		// Click on Age Under 18
		clickOnElement(loc_rdoAgeUnder18);
		// Click on Java Language
		clickOnElement(loc_chkJava);

		// Verify element is selected
		Assert.assertTrue(isElementSelected(loc_rdoAgeUnder18));
		Assert.assertFalse(isElementSelected(loc_rdoAgeOver18));
		Assert.assertTrue(isElementSelected(loc_chkJava));

		// Click on Age over 18
		clickOnElement(loc_rdoAgeOver18);
		// Click on Java Language
		clickOnElement(loc_chkJava);

		// Verify element is selected
		Assert.assertFalse(isElementSelected(loc_rdoAgeUnder18));
		Assert.assertTrue(isElementSelected(loc_rdoAgeOver18));
		Assert.assertFalse(isElementSelected(loc_chkJava));
	}

	public void clickOnElement(By locator) {
		WebElement element = driver.findElement(locator);
		element.click();
	}

	public void sendKeyToElement(By locator, String content) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(content);
	}

	public boolean isElementDisplayed(By locator) {
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			System.out.println("Element [" + locator + "] is displayed.");
			return true;
		} else {
			System.out.println("Element [" + locator + "] is not displayed.");
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
		if (element.isSelected()) {
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
