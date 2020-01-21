package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class AddIssue extends PageObject {
	@FindBy(id = "field-issues-issueName")
	private WebElement issueField;

	@FindBy(id = "field-issues-descriptionText")
	private WebElement descriptionField;

	@FindBy(id = "field-issues-value4")
	private WebElement severityField;

	@FindBy(id = "field-issues-okSubmit")
	private WebElement submitButton;

	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[1]/p")
	private WebElement errorIssue;

	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[5]/p")
	private WebElement errorSeverity;
	/***/
	@FindBy(id = "field-issues-value1")
	private WebElement assignedToField;
	
	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[2]/p")
	private WebElement assignedError;
	
	@FindBy(id = "field-issues-value2")
	private WebElement statusField;
	
	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[3]/p")
	private WebElement statusError;
	
	@FindBy(id = "field-issues-value3")
	private WebElement reasonField;
	
	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[4]/p")
	private WebElement reasonError;
	
	
	
	public AddIssue(WebDriver driver) {
		super(driver);
	}

	public void add(String issue, String description) {
		driver.get(Utils.getUrl()+"client/index.php?folder=5");
		driver.findElement(By.linkText("Add Issue")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(issueField));

		this.issueField.clear();
		this.issueField.sendKeys(issue);
		
		this.descriptionField.clear();
		this.descriptionField.sendKeys(description);
		this.submitButton.click();
	}
	
	public void add(String issue, String description, String severity) {
		driver.get(Utils.getUrl()+"client/index.php?folder=5");
		driver.findElement(By.linkText("Add Issue")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(issueField));
		
		this.issueField.clear();
		this.issueField.sendKeys(issue);
		
		this.descriptionField.clear();
		this.descriptionField.sendKeys(description);
		
		this.severityField.clear();
		this.severityField.sendKeys(severity);
		this.submitButton.click();
	}
	
	public void add(String issue, String assignedTo, String status, String reason, String description, String severity) {
		driver.get(Utils.getUrl()+"client/index.php?folder=5");
		driver.findElement(By.linkText("Add Issue")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(issueField));
		
		this.issueField.clear();
		this.issueField.sendKeys(issue);
		
		this.assignedToField.clear();
		this.assignedToField.sendKeys(assignedTo);
		
		this.statusField.clear();
		this.statusField.sendKeys(status);
		
		this.reasonField.clear();
		this.reasonField.sendKeys(reason);
		
		this.descriptionField.clear();
		this.descriptionField.sendKeys(description);
		
		this.severityField.clear();
		this.severityField.sendKeys(severity);
		
		this.submitButton.click();
	}

	public void delete() {
		driver.findElement(By.linkText("Delete Issue")).click();
		driver.findElement(By.id("field-issues-okSubmit")).click();
	}

	public WebElement getErrorIssue() {
		return errorIssue;
	}

	public WebElement getErrorSeverity() {
		return errorSeverity;
	}

	public WebElement getAssignedError() {
		return assignedError;
	}

	public void setAssignedError(WebElement assignedError) {
		this.assignedError = assignedError;
	}

	public WebElement getStatusError() {
		return statusError;
	}

	public void setStatusError(WebElement statusError) {
		this.statusError = statusError;
	}

	public WebElement getReasonError() {
		return reasonError;
	}
}