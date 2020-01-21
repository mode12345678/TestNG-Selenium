package tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobject.Login;
import utils.Utils;

public class LoginTest {
	public WebDriver driver;
	Login auth;
	private String loginCookieName = "WebIssuesSID";

	@BeforeClass(groups = {"smoke", "regression"} )
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		auth = new Login(driver);
	}

	@AfterClass(groups = {"smoke", "regression"} )
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void Should_BeNotAbleToLogin_When_ProvidingInvalidUserName() throws InterruptedException {
		auth.login("invalid", Utils.getPassword());

		Assert.assertEquals(auth.getError().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing");
	}

	@Test
	public void Should_BeNotAbleToLogin_When_ProvidingInvalidPassword() throws InterruptedException {
		auth.login(Utils.getUsername(), "invalid");

		Assert.assertEquals(auth.getError().getText(), "Incorrect value: Invalid login or password.",
				"Validation message is missing");
	}
	
	@Test(groups = {"smoke", "regression"} )
	public void Should_BeAbleToLogin_When_WithValidData() throws InterruptedException {
		auth.login(Utils.getUsername(), Utils.getPassword());
		boolean cookie = driver.manage().getCookieNamed(loginCookieName) != null;
		
		Assert.assertTrue(cookie, "Login failed");
	}
	
	@Test
	public void Should_BeAbleToLogOut_When_PressLink() throws InterruptedException {
		auth.login(Utils.getUsername(), Utils.getPassword());
		auth.logout();

		boolean cookie = driver.manage().getCookieNamed(loginCookieName) == null;
		
		Assert.assertTrue(cookie, "LogOut failed");
	}
	
	@Test
	public void Should_BeNotAbleToLogin_When_ProvidingArrayDataOfInvalidPassword() {
		ArrayList<String> data = Utils.parseExcel();
		
		for (int i = 0; i < data.size(); i+=2) {
			auth.login(data.get(i), data.get(i+1));
			Assert.assertEquals(auth.getError().getText(), "Incorrect value: Invalid login or password.",
	  				"Validation message is missing");
		}
	}
	
//	@Test
//	public void Should_BeAbleToLogOut_When_DeleteCookie() throws InterruptedException {
//		auth.login(Utils.getUsername(), Utils.getPassword());
//		
//		driver.manage().deleteCookieNamed(loginCookieName);
//		boolean cookie = driver.manage().getCookieNamed(loginCookieName) == null;
//		
//		Assert.assertTrue(cookie, "LogOut failed when deleting cookie manually");
//	}
	
}