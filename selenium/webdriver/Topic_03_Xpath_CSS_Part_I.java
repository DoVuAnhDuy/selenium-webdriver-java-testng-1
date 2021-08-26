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

public class Topic_03_Xpath_CSS_Part_I {
	WebDriver driver;
	
	// Action 
	By loc_txtName = By.id("txtFirstname");
	By loc_txtEmail = By.id("txtEmail");
	By loc_txtConfirmEmail = By.id("txtCEmail");
	By loc_txtPassword = By.id("txtPassword");
	By loc_txtConfirmPassword = By.id("txtCPassword");
	By loc_txtPhone = By.id("txtPhone");
	By loc_btnRegister = By.xpath("//div[contains(@class,'frmRegister')]//button");
	
	// Error
	By loc_txtNameError = By.id("txtFirstname-error");
	By loc_txtEmailError = By.id("txtEmail-error");
	By loc_txtConfirmEmailError = By.id("txtCEmail-error");
	By loc_txtPasswordError = By.id("txtPassword-error");
	By loc_txtConfirmPasswordError = By.id("txtCPassword-error");
	By loc_txtPhoneError = By.id("txtPhone-error");
	
	// Data
	String name = "Rhys Warren";
	String email = "Rhys.Warren@gg.com";
	String password = "7539312";
	String phone = "0129001166";
	
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01_Register_With_Empty_Data() {
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtNameError).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(loc_txtEmailError).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(loc_txtConfirmEmailError).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(loc_txtPasswordError).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(loc_txtConfirmPasswordError).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(loc_txtPhoneError).getText(), "Vui lòng nhập số điện thoại.");
	}
	
	@Test
	public void TC_02_Register_With_Invalid_Email() {
		// Input data
		driver.findElement(loc_txtName).sendKeys(name);
		driver.findElement(loc_txtEmail).sendKeys("Yahoo@abcde.com@vn");
		driver.findElement(loc_txtConfirmEmail).sendKeys(email);		
		driver.findElement(loc_txtPassword).sendKeys(password);
		driver.findElement(loc_txtConfirmPassword).sendKeys(password);
		driver.findElement(loc_txtPhone).sendKeys(phone);
		
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtEmailError).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(loc_txtConfirmEmailError).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		// Input data
		driver.findElement(loc_txtName).sendKeys(name);
		driver.findElement(loc_txtEmail).sendKeys(email);
		driver.findElement(loc_txtConfirmEmail).sendKeys("abcde@com.vn");		
		driver.findElement(loc_txtPassword).sendKeys(password);
		driver.findElement(loc_txtConfirmPassword).sendKeys(password);
		driver.findElement(loc_txtPhone).sendKeys(phone);
		
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtConfirmEmailError).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Register_With_Password_Lower_6_Characters() {
		// Input data
		driver.findElement(loc_txtName).sendKeys(name);
		driver.findElement(loc_txtEmail).sendKeys(email);
		driver.findElement(loc_txtConfirmEmail).sendKeys(email);		
		driver.findElement(loc_txtPassword).sendKeys("123");
		driver.findElement(loc_txtConfirmPassword).sendKeys("123");
		driver.findElement(loc_txtPhone).sendKeys(phone);
		
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtPasswordError).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(loc_txtConfirmPasswordError).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Register_With_Incorrect_Confirm_Password() {
		// Input data
		driver.findElement(loc_txtName).sendKeys(name);
		driver.findElement(loc_txtEmail).sendKeys(email);
		driver.findElement(loc_txtConfirmEmail).sendKeys(email);		
		driver.findElement(loc_txtPassword).sendKeys(password);
		driver.findElement(loc_txtConfirmPassword).sendKeys("4718923");
		driver.findElement(loc_txtPhone).sendKeys(phone);
		
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtConfirmPasswordError).getText(), "Mật khẩu bạn nhập không khớp");

	}
	
	@Test
	public void TC_06_Register_With_Invalid_Phone_Number() {
		// Input data
		driver.findElement(loc_txtName).sendKeys(name);
		driver.findElement(loc_txtEmail).sendKeys(email);
		driver.findElement(loc_txtConfirmEmail).sendKeys(email);		
		driver.findElement(loc_txtPassword).sendKeys(password);
		driver.findElement(loc_txtConfirmPassword).sendKeys(password);
		
		// Input data
		driver.findElement(loc_txtPhone).sendKeys(".");
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtPhoneError).getText(), "Vui lòng nhập con số");
		
		// Clear phone field
		driver.findElement(loc_txtPhone).clear();
		// Input data
		driver.findElement(loc_txtPhone).sendKeys("3464");
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtPhoneError).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		
		// Clear phone field
		driver.findElement(loc_txtPhone).clear();
		// Input data
		driver.findElement(loc_txtPhone).sendKeys("012433");
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtPhoneError).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// Clear phone field
		driver.findElement(loc_txtPhone).clear();
		// Input data
		driver.findElement(loc_txtPhone).sendKeys("012433456464356346");
		// Click on ĐĂNG KÝ button
		driver.findElement(loc_btnRegister).click();
		// Verify error message
		Assert.assertEquals(driver.findElement(loc_txtPhoneError).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
