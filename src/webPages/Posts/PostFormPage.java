package webPages.Posts;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostFormPage {
	
	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String PAGE_URL="https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement weButtonSave;
	@FindBy(xpath="//a[.='Cancel']")
	private WebElement weButtonCancel;
	@FindBy(xpath="//select[@name='post_category_id']")
	private WebElement weCategoryManu;
	@FindBy(xpath="//input[@name='title']")
	private WebElement weTitle;
	@FindBy(xpath="//textarea[@name='description']")
	private WebElement weDescription;
	@FindBy(id="title-error")
	private WebElement weErrorTitle;
	@FindBy(id="title-error")
	private WebElement weInvalidTitle;
	@FindBy(id="description-error")
	private WebElement weErrorDescription;
	@FindBy(id="description-error")
	private WebElement weInvalidDescription;
	@FindBy(xpath="//span[@id='tag_id[]-error']")
	private WebElement weErrorTag;
	@FindBy(xpath="//div[@class='invalid-feedback']")
	private WebElement weErrorContent;
	@FindBy(xpath="//iframe[@title='Rich Text Editor, content']")
	private WebElement weIframeEditor;
	@FindBy(xpath="//body[contains(@class, 'cke_editable')]")
	private WebElement weInputContent;
	@FindBy(xpath="//button[@class='btn btn-sm btn-outline-danger']")
	private WebElement weDeletePhoto;
	@FindBy(xpath="//div[@class='toast-message']")
	private WebElement weToastrDeletePhoto;
	
	public PostFormPage(WebDriver driver, WebDriverWait driverWait) {
		super();
		this.driver = driver;
		this.driverWait = driverWait;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);		
	}
	public void openPage() {
		driver.get(PAGE_URL);
	}
	
	public void clickOnSaveButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", weButtonSave); 
		Thread.sleep(1000);
		//driverWait.until(ExpectedConditions.visibilityOf(weButtonSave)); //******Ovo mi nikako ne radi, moram Thread.sleep da koristim.
		weButtonSave.click();
	}
	public void clickOnCancelButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", weButtonCancel); 
		Thread.sleep(1000);
		weButtonCancel.click();
	}
	
	public void inputStringTitle(String title) {
		weTitle.clear();
		weTitle.sendKeys(title);
	}
	
	public void inputStringDescription(String description) {
		weDescription.clear();
		weDescription.sendKeys(description);
	}
	
	public void inputStringContent(String content) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String mainWindow = driver.getWindowHandle();
		driver.switchTo().frame(weIframeEditor);
		js.executeScript("arguments[0].scrollIntoView();", weInputContent); 
		Thread.sleep(1000);
		weInputContent.clear();
		weInputContent.sendKeys(content);
		driver.switchTo().window(mainWindow);
	}
	public String getTitleInputError() {
		return weErrorTitle.getText();
	}
	
	public String getDescriptionInputError() {
		return weErrorDescription.getText();
	}
	public String getContentInputError() {
		return weErrorContent.getText();
	}
	
	public String getTagInputError() {
		return weErrorTag.getText();
	}
	
	public void clickOnTag(String tagName) throws InterruptedException {
		WebElement weTag = driver.findElement(By.xpath("//label[text()='"+tagName+"']//parent::div"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", weTag); 
		Thread.sleep(1000);
		weTag.click();
	}
	
	public void clickOnCategoryManu() {
		weCategoryManu.click();
		
	}
	public void clickOnCategoryFromMenu(String category) throws InterruptedException {
		
		WebElement weCategory = driver.findElement(By.xpath("//select/option[contains(.,'" +category+"')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driverWait.until(ExpectedConditions.visibilityOf(weCategory));
		js.executeScript("arguments[0].scrollIntoView();", weCategory); 
		
		weCategory.click();
	}
	
	public void checkImportantRatio(String option) {
		WebElement weImportant = driver.findElement(By.xpath("//label[text()='"+option+"']"));
		weImportant.click();


	}
	
	//Delete photo
	
	public void clickOnButtonDeletePhoto() throws InterruptedException {

		weDeletePhoto.click();
		Thread.sleep(3000);
		assertEquals(weToastrDeletePhoto.getText(), "Your photo has been deleted.", "Photo hasn't been deleted. ");
	}
	


		
	
	
	
}
