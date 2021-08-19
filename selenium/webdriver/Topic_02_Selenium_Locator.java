package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_FindElement_By_ID() {
		// Click on Login button
		driver.findElement(By.id("send2")).click();

		// Verify email error message
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[contains(text(),'This is a required field.')]")).isDisplayed());
	}

	@Test
	public void TC_02_FindElement_By_Class() {
		driver.navigate().refresh();

		// Click on Magento icon
		driver.findElement(By.className("large")).click();

		// Verify title
		Assert.assertEquals(driver.getTitle(), "Home page");

		// Back to Account page
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_03_FindElement_By_Name() {
		driver.navigate().refresh();

		// Click on Login button
		driver.findElement(By.name("send")).click();

		// Verify email error message
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[contains(text(),'This is a required field.')]")).isDisplayed());
	}

	@Test
	public void TC_04_FindElement_By_Tagname() {
		driver.navigate().refresh();

		// Get list of elements which tag is input
		List<WebElement> elements = driver.findElements(By.tagName("li"));

		// Verify text of each element
		for (WebElement webElement : elements) {
			System.out.println(webElement.getText());
		}
	}

	@Test
	public void TC_05_FindElement_By_LinkText() {
		driver.navigate().refresh();

		// Click on MOBILE
		driver.findElement(By.linkText("MOBILE")).click();

		// Verify title
		Assert.assertEquals(driver.getTitle(), "Mobile");

		// Back to Account page
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_06_FindElement_By_PartialLinkText() {
		driver.navigate().refresh();

		// Click on Forgot link
		driver.findElement(By.partialLinkText("Forgot")).click();

		// Verify title
		Assert.assertEquals(driver.getTitle(), "Forgot Your Password");

		// Back to Account page
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_07_FindElement_By_Css() {
		driver.navigate().refresh();

		// Click on Login button
		driver.findElement(By.cssSelector("#send2")).click();

		// Verify email error message
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[contains(text(),'This is a required field.')]")).isDisplayed());
	}

	@Test
	public void TC_08_FindElement_By_Xpath() {
		driver.navigate().refresh();

		// Click on Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Verify email error message
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[contains(text(),'This is a required field.')]")).isDisplayed());
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
