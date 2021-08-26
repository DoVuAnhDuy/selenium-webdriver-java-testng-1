package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Command {
	WebDriver driver;

	// Action footer
	By loc_lnkFooterAccount = By.xpath("//div[@class='footer-container']//a[@title='My Account']");

	// Action Login page
	By loc_btnCreateAccount = By.xpath("//a[@title='Create an Account']");

	@BeforeTest
	public void beforeTest() {
		String filePath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", filePath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com");

		// Click on Account
		driver.findElement(loc_lnkFooterAccount).click();
	}

	@Test
	public void TC_01_Verify_URL() {
		// Verify current url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		// Click on Create An Account
		driver.findElement(loc_btnCreateAccount).click();

		// Verify current url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_Title() {
		// Verify current title
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		// Click on Create An Account
		driver.findElement(loc_btnCreateAccount).click();

		// Verify current title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Verify_Navigate_Function() {
		// Click on Create An Account
		driver.findElement(loc_btnCreateAccount).click();
		
		// Verify current url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		// Back to Login page
		driver.navigate().back();
		
		// Verify current url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		// Forward to Register page
		driver.navigate().forward();
		
		// Verify current title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_04_Verify_Page_Source_Code() {
		
		// Get current page source
		String strLoginPageSource = driver.getPageSource();
		
		// Verify page source
		Assert.assertTrue(strLoginPageSource.contains("Login or Create an Account"));
		
		// Click on Create An Account
		driver.findElement(loc_btnCreateAccount).click();
		
		// Get current page source
		String strRegisterPageSource = driver.getPageSource();
		
		// Verify page source
		Assert.assertTrue(strRegisterPageSource.contains("Create an Account"));
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
