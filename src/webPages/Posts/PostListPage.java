package webPages.Posts;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.Advice.Return;

public class PostListPage {

	
	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String PAGE_URL="https://testblog.kurs-qa.cubes.edu.rs/admin/posts";
	@FindBy(xpath="//a[contains(.,'Your Profile')]")
	private WebElement weYourProfile;
	@FindBy(xpath="//input[@name='title']")
	private WebElement weTitle;
	@FindBy(xpath="//i[@class='fas fa-plus-square']")
	private WebElement weAddNewPost;
	@FindBy(xpath="//div[@id='entities-list-table_filter']//input")
	private WebElement weSearch;
	@FindBy(xpath="//div[@id='entities-list-table_length']")
	private WebElement weShow;
	@FindBy(xpath="//button[text()='Cancel']")
	private WebElement weDialogCancel;
	@FindBy(xpath="//button[text()='Delete']")
	private WebElement weDialogDelete;
	@FindBy(xpath="//p[@class='lead']")
	private WebElement weViewDescription;
	@FindBy(xpath="//button[contains(.,'Set as Important')]")
	private WebElement weSetAsImportant;
	@FindBy(xpath=" //button[contains(.,'Set as Unimportant')]")
	private WebElement weSetAsUnimportant;
	@FindBy(xpath="//button[contains(.,'Disable')]")
	private WebElement weDisable;
	@FindBy(xpath="//button[contains(.,'Enable')]")
	private WebElement weEnable;
	@FindBy(xpath="//div[@class='toast-message']")
	private WebElement weToastMessage;
	@FindBy(xpath="//div[@class='toast toast-success']")
	private WebElement weToastMessageSuccess;
	
	
	public PostListPage(WebDriver driver, WebDriverWait driverWait) {
		super();
		this.driver = driver;
		this.driverWait = driverWait;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	
	}
	public void openPage() {
		this.driver.get(PAGE_URL);
	}

	//Metode za testiranje linkova sa strane:
	
	public void openLinkParentInManu(String title) {
		
		WebElement weMenu = driver.findElement(By.xpath("//p[text()='"+title+"']//ancestor::li[2]"));
		
		if(!weMenu.getAttribute("class").toString().equalsIgnoreCase("nav-item has-treeview menu-open")) {
    		
    		weMenu.click();
    	}
	}
	public void clickOnLinkInMenu(String title)	{
			
		WebElement weLink = driver.findElement(By.xpath("//p[text()='"+title+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(weLink));
		weLink.click();
	}

	//Metode za testiranje navigacionih linkova:

	public void clickOnNavigationLink(String title) {
		
		WebElement weLink = driver.findElement(By.xpath("//a[text()='"+title+"']"));
		weLink.click();
		
	}
	
	//Metode za testiranje ikonice User:

	public void openLinkFontAwesome () {
	 WebElement weFontAwesome= driver.findElement(By.xpath("//i[@class='far fa-user']"));
		weFontAwesome.click();
}

	
	public void clickOnLinkYourProfile () {
		weYourProfile.click();
	
}

	public void clickOnLinkLogOut () {
	WebElement weLogOut= driver.findElement(By.xpath("//a[contains(.,'Log Out')]"));
	weLogOut.click();

}
	//Metode za web element Title:
	
	public void enterTitle(String title) {
	weTitle.sendKeys(title);
}

	public boolean isPostWithTitleInList(String title) throws InterruptedException {

	ArrayList<WebElement> weSearchTitle = (ArrayList<WebElement>) driver.findElements(By.xpath("//table[@id='entities-list-table']//tr/td[5][contains(text(),'"+title+"')]"));
	return !weSearchTitle.isEmpty();
	
	    }
	
	public void clearTitle() {
	weTitle.clear();
	
}

	//Metode za web element Author:
	
	public void openAuthorManu() {
	
	WebElement weAuthorMenu = driver.findElement(By.xpath("//label[text()='Author']//parent::div"));

	if(!weAuthorMenu.getAttribute("class").toString().equalsIgnoreCase("select2 select2-container select2-container--bootstrap4 select2-container--below select2-container--open")) {
		
		weAuthorMenu.click();
	}

}
	public void clickOnAuthorFromList(String author) throws InterruptedException {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement weAuthor = driver.findElement(By.xpath("//option[text()='"+author+"']"));
	js.executeScript("arguments[0].scrollIntoView();", weAuthor); 
	//driverWait.until(ExpectedConditions.visibilityOf(weAuthor)); ne radi
	Thread.sleep(1000);
	weAuthor.click();
}

	public boolean isPostWithAuthorInList(String author) {
		
	ArrayList<WebElement> weSearchAuthor = (ArrayList<WebElement>) driver.findElements(By.xpath("//table[@id='entities-list-table']//tr/td[6][contains(text(),'"+author+"')]"));
	
	return !weSearchAuthor.isEmpty();
	
	    }
	//Metode za web element Category:
	
	public void openCategoryManu() {
	 
	 WebElement weCategoryMenu = driver.findElement(By.xpath("//label[text()='Category']//parent::div"));
	 if(!weCategoryMenu.getAttribute("class").toString().equalsIgnoreCase("select2 select2-container select2-container--bootstrap4 select2-container--below select2-container--open")) {
	
		weCategoryMenu.click();
		}
	
}
	public void clickOnCategoryFromList(String category) throws InterruptedException {
		
		WebElement weCategory = driver.findElement(By.xpath("//option[text()='"+category+"']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", weCategory); 
		//driverWait.until(ExpectedConditions.visibilityOf(weCategory));
		Thread.sleep(1000);
		weCategory.click();
	}
	
	public boolean isPostWithCategoryInList(String category) {

		ArrayList<WebElement> weSearchCategory = (ArrayList<WebElement>) driver.findElements(By.xpath("//table[@id='entities-list-table']//tr/td[7][contains(text(),'"+category+"')]"));
		return !weSearchCategory.isEmpty();
 		    }
	
	//Metode za web element in Search Form Important:
	
	public void openImportantMenu() {
	 
	 WebElement weImportantMenu = driver.findElement(By.xpath("//label[text()='Important']//parent::div"));

		weImportantMenu.click();
	}
		
	public void clickOnOptionImportantFromList(String option) throws InterruptedException {
			
			WebElement weOption = driver.findElement(By.xpath("//option[text()='"+option+"']"));
			//driverWait.until(ExpectedConditions.visibilityOf(weOption));
			Thread.sleep(1000);
			weOption.click();
 
}
	public boolean isPostWithImportantInList(String option) {

		ArrayList<WebElement> weSearchImportant = (ArrayList<WebElement>) driver.findElements(By.xpath("//table[@id='entities-list-table']//tr/td[3][contains(.,'"+option+"')]"));
		return !weSearchImportant.isEmpty();
 
	}
	//Metode za web element Status:
	
	public void openStatusMenu() throws InterruptedException {
		 
		 WebElement weStatusMenu = driver.findElement(By.xpath("//label[text()='Status']//parent::div"));
		 Thread.sleep(1000);
		 weStatusMenu.click();
			
	 }
			
	public void clickOnStatusFromList(String status) throws InterruptedException {
				
		WebElement weStatus = driver.findElement(By.xpath("//option[text()='"+status+"']"));
		//driverWait.until(ExpectedConditions.visibilityOf(weStatus));
		Thread.sleep(1000);
		weStatus.click();
	 
	}
	public boolean isPostWithStatusInList(String status) {

		ArrayList<WebElement> weSearchStatus = (ArrayList<WebElement>) driver.findElements(By.xpath("//table[@id='entities-list-table']//tr/td[4][contains(.,'"+status+"')]"));
		return !weSearchStatus.isEmpty();
 }
	//Metode za web element Tag:
	
	public void openTagMenu() {
			 
		WebElement weTagMenu = driver.findElement(By.xpath("//label[text()='With Tag']//parent::div"));
		weTagMenu.click();
	}
				
	public void clickOnTagFromList(String tag) throws InterruptedException {
					
		WebElement weTag = driver.findElement(By.xpath("//option[text()='"+tag+"']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", weTag); 
		//driverWait.until(ExpectedConditions.visibilityOf(weTag));
		Thread.sleep(1000);
		weTag.click();
	}
			
	public boolean isPostWithTagInList(String tag) {
				
		ArrayList<WebElement> weSearchTag = (ArrayList<WebElement>) driver.findElements(By.xpath("//table[@id='entities-list-table']//tr/td[8][contains(.,'"+tag+"')]"));
		return !weSearchTag.isEmpty();
	}
		
	public void removeTagFromSearchField() {
			
		WebElement weTagMenu = driver.findElement(By.xpath("//label[text()='With Tag']//parent::div"));
		weTagMenu.click();
		WebElement weX = driver.findElement(By.xpath("//span[@class='select2-selection__choice__remove']"));
		weX.click();
}
		
		public void clickOnAddNewPost() {
		weAddNewPost.click();
	}
	
		// metode za testiranje Search polja u okviru forme All Posts:
		
		public void clickOnSearchInAllPosts() {
			weSearch.clear();
			weSearch.click();
		}
		public void searchPerImportant(String Yes) throws InterruptedException {
			Thread.sleep(1000);
			weSearch.sendKeys("Yes");			
		}
		public void searchPerTitle(String title) throws InterruptedException {
			Thread.sleep(1000);
			weSearch.sendKeys(title);
					
		}
		
		// metode za testiranje Show entries u okviru forme All Posts:
	
		public void clickOnShowEntries() {
			weShow.click();
		}
		
		public void chooseOptionShowEntries(String option) throws InterruptedException {
						
			WebElement weShowOptions = driver.findElement(By.xpath("//div[@id='entities-list-table_length']//select[option]"));
			weShowOptions.click();
			
			WebElement weOption = driver.findElement(By.xpath("//div[@id='entities-list-table_length']//select//option[.='"+option+"']"));
			weOption.click();
			Thread.sleep(1000);
			WebElement weShowing = driver.findElement(By.xpath("//div[@id='entities-list-table_info']"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			driverWait.until(ExpectedConditions.visibilityOf(weShowing));
			js1.executeScript("arguments[0].scrollIntoView();", weShowing); 
		
		}
		
		//Metode za testiranje brisanja posta iz liste postova
		
		public void clickOnButtonDeletePost(String title) throws InterruptedException {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement weDeleteButton = driver.findElement(By.xpath("//table[@id='entities-list-table']//tr/td[5][contains(.,'"+title+"')]//ancestor::tr//td[12]//div[1]/button[1]"));
			js.executeScript("arguments[0].scrollIntoView();", weDeleteButton);
			//driverWait.until(ExpectedConditions.visibilityOf(weDeleteButton)); //za ovaj test, ovaj deo koda nekad radi, a nekad ne
			Thread.sleep(2000);
			weDeleteButton.click();
			Thread.sleep(2000);
		}
		
		public void clickOnDialogCancel() {
			weDialogCancel.click();
		}
		
		public void clickOnDialogDelete() {
			weDialogDelete.click();
		}
		
		public boolean isPostInList(String title) {

			ArrayList<WebElement> wePost = (ArrayList<WebElement>) driver.findElements(By.xpath("//table[@id='entities-list-table']//tr/td[5][contains(text(),'"+title+"')]"));
			return !wePost.isEmpty();
		}
		
		public void clickOnUpdatePost(String title) throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement weUpdateButton = driver.findElement(By.xpath("//table[@id='entities-list-table']//tr/td[5][contains(.,'"+title+"')]//ancestor::tr//td[12]//div[1]/a[2]"));
			js.executeScript("arguments[0].scrollIntoView();", weUpdateButton);
			//driverWait.until(ExpectedConditions.visibilityOf(weDeleteButton)); //za ovaj test, ovaj deo koda nekad radi, a nekad ne
			Thread.sleep(2000);
			weUpdateButton.click();
			Thread.sleep(2000);
			
		}
		
		public void clickOnButtonViewPost(String title) throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement weViewButton = driver.findElement(By.xpath("//table[@id='entities-list-table']//tr/td[5][contains(.,'"+title+"')]//ancestor::tr//td[12]//div[1]/a[1]"));
			js.executeScript("arguments[0].scrollIntoView();", weViewButton);
			//driverWait.until(ExpectedConditions.visibilityOf(weDeleteButton)); //za ovaj test, ovaj deo koda nekad radi, a nekad ne
			Thread.sleep(2000);
			weViewButton.click();
			Thread.sleep(2000);
			
		}
		
		//pokusala sam u sledecoj metodi da lociram Description na "view single post" strani da bih uporedila
		//tekst descriptiona sa updejtovanim tekstom, ali nisam uspela
	        public String viewPageCheckDescription() throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", weViewDescription);
			Thread.sleep(2000);
			weViewDescription.click();
			return weViewDescription.getText();
			
		}
	        
	   //Metode za testiranje buttona (kolona Actions) za stikliranje Important u tabeli "All Posts"
			
			public void clickOnButtonImportant(String title) throws InterruptedException {
				
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement weImportantButton = driver.findElement(By.xpath("//table[@id='entities-list-table']//tr/td[5][contains(.,'"+title+"')]//ancestor::tr//td[12]//div[2]/button[2]"));
			js.executeScript("arguments[0].scrollIntoView();", weImportantButton);
			//driverWait.until(ExpectedConditions.visibilityOf(weDeleteButton)); ne radi
			Thread.sleep(2000);
			weImportantButton.click();
			Thread.sleep(2000);
			if(!weImportantButton.getAttribute("class").toString().equalsIgnoreCase("fas fa-bookmark")) {
				Thread.sleep(2000);
				weSetAsImportant.click();
				Thread.sleep(2000);
				assertEquals(weToastMessage.getText(), "Post has been set as important.", "Wrong toast message");
			}
			else if(weImportantButton.getAttribute("class").toString().equalsIgnoreCase("fas fa-times")) {
				Thread.sleep(2000);
				weSetAsUnimportant.click();
				Thread.sleep(2000);
				assertEquals(weToastMessageSuccess.getText(), "Post has been set as unimportant.", "Wrong toast message");
			}        
			}
	 	
	   //Metode za testiranje buttona (kolona Actions) za stikliranje Statusa u tabeli "All Posts"
		
		public void clickOnButtonStatus(String title) throws InterruptedException {
			
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement weStatusButton = driver.findElement(By.xpath("//table[@id='entities-list-table']//tr/td[5][contains(.,'"+title+"')]//ancestor::tr//td[12]//div[2]/button[1]"));
		js.executeScript("arguments[0].scrollIntoView();", weStatusButton);
		//driverWait.until(ExpectedConditions.visibilityOf(weDeleteButton)); ne radi
		Thread.sleep(2000);
		weStatusButton.click();
		Thread.sleep(2000);
		
		if(!weStatusButton.getAttribute("class").toString().equalsIgnoreCase("fas fa-check")) {
			//driverWait.until(ExpectedConditions.visibilityOf(weDisable));
			Thread.sleep(2000);
			weDisable.click();
			Thread.sleep(2000);
			
			assertEquals(weToastMessage.getText(), "Post has been disabled", "Wrong toast message");
		}
		else //if	(!weStatusButton.getAttribute("class").toString().equalsIgnoreCase("fas fa-minus-circle")) {
			//driverWait.until(ExpectedConditions.visibilityOf(weEnable));
		{
			Thread.sleep(2000);
			weEnable.click();
			Thread.sleep(2000);
			assertEquals(weToastMessageSuccess.getText(), "Post has been enabled", "Wrong toast message");
		}        
		}
 	
  public void clickOnDialogDisable() {
	   driverWait.until(ExpectedConditions.visibilityOf(weDisable));
	   weDisable.click();
	}
	   
  public void clickOnDialogEnable() {
	   driverWait.until(ExpectedConditions.visibilityOf(weEnable));
	   weEnable.click();
	}      
	     
	        
}
	
	

	
	
	
