package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pageobject.*;
import utils.Utils;

public class issuesTest {
	public WebDriver driver;
	public AddIssue issue;

	@Test
	public void Should_BeAbleToCreateIssue_When_ProvidingCorrectData() {
		String issueName = Utils.randomText(10) + Utils.randomInt(100, 999);
		
		issue.add(issueName, Utils.randomText(50), "2");
//		issue.delete();
	}

	@Test // (timeOut=1000) //(enabled=false)
	public void Should_NotBeAbleToCreateIssue_When_IssueNameIsNotProvided()  {
		issue.add("", Utils.randomText(100));

		Assert.assertEquals(issue.getErrorIssue().getText(), "Incorrect value: Required value is missing.");
	}
	
	@Test // (timeOut=1000) //(enabled=false)
	public void Should_NotBeAbleToCreateIssue_When_IssueNameIsEmpty(){
		issue.add("", Utils.randomText(100));

		Assert.assertEquals(issue.getErrorIssue().getText(), "Incorrect value: Required value is missing.");
	}

	@Test
	public void Should_NotBeAbleToCreateIssue_When_IssueSeverityIsInvalid()  {
		issue.add("random random", "severity", "30");

		Assert.assertEquals(issue.getErrorSeverity().getText(), "Incorrect value: Number is too big.");
	}
	
	@Test
	public void Should_NotBeAbleToCreateIssue_When_AssignedToIsInvalid(){
		issue.add("title", "~~~", "Active", "Fixed", Utils.randomText(50), "2");
	
		Assert.assertEquals(issue.getAssignedError().getText(), "Incorrect value: No matching item is selected.", "MESSAGE Assigned failed");
	}
	
	@Test
	public void Should_NotBeAbleToCreateIssue_When_StatusIsInvalid() {
		issue.add("title", "Greta", "~~~", "Fixed", Utils.randomText(50), "2");
	
		Assert.assertEquals(issue.getStatusError().getText(), "Incorrect value: No matching item is selected.", "MESSAGE Status failed");
	}
	
	@Test
	public void Should_NotBeAbleToCreateIssue_When_ReasonIsInvalid(){
		issue.add("title", "Greta", "Active", "aaa", Utils.randomText(50), "2");
	
		Assert.assertEquals(issue.getReasonError().getText(), "Incorrect value: No matching item is selected.", "MESSAGE Reason failed");
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://qaontime.com/register");

		Login auth = new Login(driver);
		auth.login("modestas.liulys@gmail.com", "modestas.liulys@gmail.com");
		
		issue = new AddIssue(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

//	@Test(expectedExceptions = ArithmeticException.class)
//	public void divideByZero() {
//		int i = 1/0;
//	}
}