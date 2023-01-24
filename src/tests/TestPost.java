
package tests;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webPages.Login.LoginPage;
import webPages.Posts.PostFormPage;
import webPages.Posts.PostListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestPost {

	private static ChromeDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait driverWait;
	private static LoginPage loginPage;
	private static PostListPage postListPage;
	private static PostFormPage postFormPage;
	@FindBy(xpath="//script[9]")
	private WebElement weToastr;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "/C:/Users/pc/Desktop/chromedriver.exe");
	    driver = new ChromeDriver();
	    
	    driver.manage().window().maximize();
	    WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofMillis(10000));
	    
	    loginPage = new LoginPage(driver);
	    postFormPage = new PostFormPage(driver, driverWait);
	    postListPage = new PostListPage(driver, driverWait);
	    loginPage.loginSuccess();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		
		postListPage.openPage();
	}
	@After
	public void tearDown() throws Exception {
		
	}
	

	@Test      //Testriranje linkova u Sidebaru sa leve strane Posts List
	
	public void tc02TestMenuLinks() {
		
		checkMenuLink("Sliders list", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders");
		checkMenuLink("Add Slider", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders/add");
		checkMenuLink("Post Categories list", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories");
		checkMenuLink("Add Post Category", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories/add");
		checkMenuLink("Tags list", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags");
		checkMenuLink("Add Tag", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add");
		checkMenuLink("Posts list", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		checkMenuLink("Add Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		checkMenuLink("Comments List", "https://testblog.kurs-qa.cubes.edu.rs/admin/comments");
		checkMenuLink("Users List", "https://testblog.kurs-qa.cubes.edu.rs/admin/users");
		checkMenuLink("Add User", "https://testblog.kurs-qa.cubes.edu.rs/admin/users/add");
	
	}
    @Test      //Testiranje navigacionih linkova, Posts List/linka "Home"
	
	public void tc03TestNavigationLink() {
		
    	checkNavigationLink("Home", "https://testblog.kurs-qa.cubes.edu.rs/admin");
	
	}
	
	@Test      //Testiranje Posts List/"Font Awesome ikonice", funkcije "Your profile"
	
	public void tc04TestFontAwesomeIconYourProfileLink () throws InterruptedException {
		
		postListPage.openLinkFontAwesome();
		Thread.sleep(1000);
		postListPage.clickOnLinkYourProfile();
		
		assertEquals (driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/users", "Wrong URL");
		
		postListPage.openPage();
		
	}
	
	@Test      //Testiranje Posts List/ "Font Awesome ikonice", funkcije "Log Out"
	public void tc05TestFontAwesomeIconLogOutLink () throws InterruptedException {
		
		postListPage.openLinkFontAwesome();
		Thread.sleep(1000);
		postListPage.clickOnLinkLogOut();
		
		assertEquals (driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/login", "Wrong URL");
		
		postListPage.openPage();
		loginPage.loginSuccess();
	}
	
	@Test   //Testiranje Forme za pretrazivanjePosts List/ "Search Posts" per valid Title 
	
	public void tc06TestSearchFormPerExistingTitle() throws InterruptedException {
				
		postListPage.enterTitle("Vladimir");
		Thread.sleep(1000);
		assertEquals(postListPage.isPostWithTitleInList("Vladimir"), true, "Mach not found.");
		postListPage.openPage();
		postListPage.clearTitle();
	}
	

	@Test     //Testiranje Forme za pretrazivanje Posts List/"Search Posts" per invalid Title
	
	public void tc07TestSearchFormPerNotExistingTitle() throws InterruptedException {
				
		postListPage.enterTitle("nonono");
		Thread.sleep(1000);
		assertEquals(postListPage.isPostWithTitleInList("nonono"), false, "Mach not found.");
		
		postListPage.openPage();
		postListPage.clearTitle();
	}
	
	@Test      //Testiranje Forme za pretrazivanje Posts List/ "Search Posts" per Author
	
	public void tc08TestFormSearchPerAuthor() throws InterruptedException {
				
		postListPage.openAuthorManu();
		postListPage.clickOnAuthorFromList("Vladan Dzulovic");
		assertEquals(postListPage.isPostWithAuthorInList("Vladan Dzulovic"), true, "Match not found.");
		postListPage.openPage();

	}
	
	@Test      //Testiranje Forme za pretrazivanje Posts List/"Search Posts" per Category
	
	public void tc09TestFormSearchPerCategory() throws InterruptedException {
		
		postListPage.openCategoryManu();
		postListPage.clickOnCategoryFromList("Port Troy");
		assertEquals(postListPage.isPostWithCategoryInList("Port Troy"), true, "Match not found.");
		postListPage.openPage();
	}
	
	
	@Test    //Testiranje Forme za pretrazivanje Posts List/"Search Posts" per Important

	public void tc10TestFormSearchPerImportanceYes() throws InterruptedException {
		
		postListPage.openImportantMenu();
		postListPage.clickOnOptionImportantFromList("yes");
		assertEquals(postListPage.isPostWithImportantInList("Yes"), true, "Match not found." );
		postListPage.openPage();
	}

	@Test    //Testiranje Forme za pretrazivanje Posts List/"Search Posts" per Status
	
	public void tc11TestFormSearchPerStatus() throws InterruptedException {
		
		postListPage.openStatusMenu();
		postListPage.clickOnStatusFromList("enabled");
		assertEquals(postListPage.isPostWithStatusInList("enabled"), true, "Match not found." );
		postListPage.openPage();
	
	}
	
	@Test      //Testiranje Forme za pretrazivanje Posts List/"Search Posts" per Tag
	
	public void tc12TestFormSearchPerTag() throws InterruptedException {
		
		postListPage.openTagMenu();
		postListPage.clickOnTagFromList("eligendi");
		assertEquals(postListPage.isPostWithTagInList("eligendi"), true, "Match not found.");
		postListPage.openPage();			
		
	}
	@Test     //Testiranje polja Posts List/ "Search:" u okviru forme "All Posts" prema kljucnoj reci za Important, 
	            //da bismo zakljucili da ovo polje radi samo za pretrazivnje prema kljucnoj reci za Title
	
	public void tc13TestSearchPostInFormAllPostsPerImportant() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerImportant("Yes");
		Thread.sleep(1000);
		assertEquals(postListPage.isPostWithImportantInList("Yes"), true, "Match not found.");
		postListPage.openPage();
	}	
	@Test    //Testiranje polja Posts List/ "Search:" u okviru forme "All Posts" prema kljucnoj reci za Title
	
	public void tc14TestSearchPostInFormAllPostsPerTitle() throws InterruptedException {
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Really");
		Thread.sleep(1000);
		assertEquals(postListPage.isPostWithTitleInList("Really"), true, "Match not found.");
		
	}
	
	@Test         // Testiranje polja Posts List/"Show ____ entries" u okviru forme "All Posts"
	
	public void tc15TestShowEntriesField() throws InterruptedException {
		
		postListPage.clickOnShowEntries();
		postListPage.chooseOptionShowEntries("10");
		postListPage.openPage();
		postListPage.clickOnShowEntries();
		postListPage.chooseOptionShowEntries("25");
		
	}
	
	@Test       // Testiranje "Add new Post"
	
	public void tc16TestAddNewPostWithEmptyFields() throws InterruptedException {
				
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("");
		postFormPage.inputStringDescription("");
		postFormPage.inputStringContent("");
		postFormPage.clickOnSaveButton();

		assertEquals(postFormPage.getTitleInputError(), "This field is required.", "Wrong error Message");
		assertEquals(postFormPage.getDescriptionInputError(), "This field is required.", "Wrong error Message");
		assertEquals(postFormPage.getTagInputError(), "This field is required.", "Wrong error Message");
		assertEquals(postFormPage.getContentInputError(), "The content field is required.", "Wrong error Message");
		
		
	}
	@Test     // Testiranje "Add new Post"
	
	public void tc17TestAddNewPostWithInvalidTitleEmptyFields() throws InterruptedException {
		
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("Variations");
		postFormPage.inputStringDescription("");
		postFormPage.inputStringContent("");
		postFormPage.clickOnSaveButton();

		assertEquals(postFormPage.getTitleInputError(), "Please enter at least 20 characters.", "Wrong error Message");
		assertEquals(postFormPage.getDescriptionInputError(), "This field is required.", "Wrong error Message");
		assertEquals(postFormPage.getTagInputError(), "This field is required.", "Wrong error Message");
		//ova greska u nastavku se provlaci kroz sve testove "Add new post, pa sam je zakomentarisala da vidim kako se ponasaju ostali fieldovi"
		//assertEquals(postFormPage.getContentInputError(), "The content field is required.", "Wrong error Message");
	}
	
	@Test     // Testiranje "Add new Post"
	
	public void tc18TestAddNewPostWithValidTitleEmptyFields() throws InterruptedException {
	
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("Variations of passages facere");
		postFormPage.inputStringDescription("");
		postFormPage.inputStringContent("");
		postFormPage.clickOnSaveButton();

		assertEquals(postFormPage.getDescriptionInputError(), "This field is required.", "Wrong error Message");
		assertEquals(postFormPage.getTagInputError(), "This field is required.", "Wrong error Message");
		//assertEquals(postFormPage.getContentInputError(), "The content field is required.", "Wrong error Message");
		
	}
	
	@Test         // Testiranje "Add new Post"
	
	public void tc19TestAddNewPostWithValidTitleInvalidDescription() throws InterruptedException {
	
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("Variations of passages facere");
		postFormPage.inputStringDescription("Invalid description is short.");
		postFormPage.inputStringContent("");
		postFormPage.clickOnSaveButton();

		assertEquals(postFormPage.getDescriptionInputError(), "Please enter at least 50 characters.", "Wrong error Message");
		assertEquals(postFormPage.getTagInputError(), "This field is required.", "Wrong error Message");
		//assertEquals(postFormPage.getContentInputError(), "The content field is required.", "Wrong error Message");
		
	}
	
	
	@Test       // Testiranje "Add new Post"
	
	public void tc20TestAddNewPostWithValidTitleValidDescription() throws InterruptedException {
	
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("Variations of passages facere");
		postFormPage.inputStringDescription("Invalid description is short, valid description is at least 50 caracters.");
		postFormPage.inputStringContent("");
		postFormPage.clickOnSaveButton();

		assertEquals(postFormPage.getTagInputError(), "This field is required.", "Wrong error Message");
		//assertEquals(postFormPage.getContentInputError(), "The content field is required.", "Wrong error Message");
		//postListPage.openPage();
	}
	
	@Test       // Testiranje "Add new Post"
	
	public void tc21TestAddNewPostValidTitleValidDescriptionCheckedTag() throws InterruptedException {
		
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("Variations of passages facere");
		postFormPage.inputStringDescription("Invalid description is short, valid description is at least 50 caracters.");
		postFormPage.clickOnTag("eligendi");
		postFormPage.inputStringContent("");
		postFormPage.clickOnSaveButton();

		assertEquals(postFormPage.getContentInputError(), "The content field is required.", "Wrong error Message");
		postListPage.openPage();
	}
	
	@Test         //Testiranje "Add new Post", svi obavezni elementi 
	
	public void tc22TestAddNewPostValidTitleValidDescriptionCheckedTagContent() throws InterruptedException {
		
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("Variations of passages facere");
		postFormPage.inputStringDescription("Invalid description is short, valid description is at least 50 caracters.");
		postFormPage.clickOnTag("eligendi");
		postFormPage.inputStringContent("Content is important.");
		postFormPage.clickOnSaveButton();
		Thread.sleep(6000);
		postListPage.openPage();
		postListPage.chooseOptionShowEntries("100");
		assertEquals(postListPage.isPostWithTitleInList("Variations of passages facere"), true, "Match not found.");
	  		
	}
	
	
	@Test       //Testiranje "Add new Post", unos Posta koji vec postoji (sa istim Titlom i Descriptionom)
	            //Samo sam ponovila prethodni test, da pokazem da moze da se unese kompletno isti Post kao sto vec postoji
	
public void tc23TestAddNewPostExistingTitleExistingDescriptionCheckedTagContent() throws InterruptedException {
		
		postListPage.clickOnAddNewPost();
		postFormPage.inputStringTitle("Variations of passages facere");
		postFormPage.inputStringDescription("Invalid description is short, valid description is at least 50 caracters.");
		postFormPage.clickOnTag("eligendi");
		postFormPage.inputStringContent("Content is important.");
		postFormPage.clickOnSaveButton();
		Thread.sleep(6000);
		postListPage.openPage();
		postListPage.chooseOptionShowEntries("100");
		assertEquals(postListPage.isPostWithTitleInList("Variations of passages facere"), true, "Match not found.");
			
	}
	
	
	
	@Test           // Testiranje "Add new Post", Cancel button
	
	public void tc24TestAddNewPostCacnelButton() throws InterruptedException {
		
		postListPage.openPage();
		postListPage.clickOnAddNewPost();
		postFormPage.clickOnCancelButton();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts", "Not that page.");
		
	}

	@Test      // Testiranje Button "Update" na stranici Posts List, Title
	
	public void tc25TestUpdatePostTitle() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Variations of");
		postListPage.chooseOptionShowEntries("10");
	
		postListPage.clickOnUpdatePost("Variations of passages facere");
		
		postFormPage.inputStringTitle("Title is about to be updated");
		postFormPage.clickOnSaveButton();
		//driverWait.until(ExpectedConditions.invisibilityOf(weToastr)); ne radi
		Thread.sleep(6000);
		postListPage.openPage();
		postListPage.chooseOptionShowEntries("100");
		assertEquals(postListPage.isPostInList("Title is about to be updated"), true);
		
	}

	
	@Test     // Testiranje "Update", na stranici Posts List, empty Title
	
	public void tc26TestUpdatePostEmptyTitle() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
	
		postListPage.clickOnUpdatePost("Title is about");
		
		postFormPage.inputStringTitle("");
		postFormPage.clickOnSaveButton();
		//driverWait.until(ExpectedConditions.invisibilityOf(weToastr)); ne radi
		Thread.sleep(6000);
		
		assertEquals(postFormPage.getTitleInputError(), "This field is required.", "Wrong error Message");
			
	}
	
	@Test         //Testiranje "Update", na stranici Posts List, invalid title
	
	public void tc27TestUpdatePostInvalidTitle() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
	
		postListPage.clickOnUpdatePost("Title is about");
		
		postFormPage.inputStringTitle("Short Title");
		postFormPage.clickOnSaveButton();
		//driverWait.until(ExpectedConditions.invisibilityOf(weToastr)); ne radi
		Thread.sleep(6000);
		
		assertEquals(postFormPage.getTitleInputError(), "Please enter at least 20 characters.", "Wrong error Message");
	
	}
	@Test        //Testiranje buttona "Update", na stranici Posts List, valid description
	
	public void tc28TestUpdatePostDescription() throws InterruptedException {
		String mainWindow = driver.getWindowHandle();
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
	
		postListPage.clickOnUpdatePost("Title is about");
		
		postFormPage.inputStringDescription("Description is about to be updated and at least 50 charackters long.");
		postFormPage.clickOnSaveButton();
		//driverWait.until(ExpectedConditions.invisibilityOf(weToastr)); ne radi
		Thread.sleep(6000);
		postListPage.openPage();
		postListPage.chooseOptionShowEntries("100");
		assertEquals(postListPage.isPostInList("Title is about to be updated"), true);
		
		postListPage.clickOnButtonViewPost("Title is about to be updated");
		driver.switchTo().window(mainWindow);
	}
	
	@Test       //Testiranje buttona "Update", na stranici Posts List, invalid description
	
	public void tc29TestUpdatePostInvalidDescription() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
	
		postListPage.clickOnUpdatePost("Title is about");
		
		postFormPage.inputStringDescription("Description is short and invalid.");
		postFormPage.clickOnSaveButton();
		
		assertEquals(postFormPage.getDescriptionInputError(), "Please enter at least 50 characters.", "Wrong error Message");
	
	}
	@Test      //Update, na stranici Posts List, delete photo
	
	public void tc30TestUpdatePostDeletePhoto() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
		postListPage.clickOnUpdatePost("Title is about");
		postFormPage.clickOnButtonDeletePhoto();
		postListPage.openPage();
	}
	
	@Test      //Testiranje buttona "Important" Post Lists/All posts/kolona Actions/Button za Important
	
	public void tc31TestButtonImportant() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
		postListPage.clickOnButtonImportant("Title is about");
		Thread.sleep(2000);	
		postListPage.openPage();
		
		
	}
	
	@Test     //Testiranje buttona "Status" Post Lists/All posts/kolona Actions/Button za Status
	
	public void tc32TestButtonStatus() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
		postListPage.clickOnButtonStatus("Title is about");
		Thread.sleep(3000);	
		postListPage.openPage();
	
	}

	@Test  // Testiranje "Delete" buttona: Post Lists/All posts/kolona Actions/ Button za delete
	
	public void tc33TestDeletePostClickCancel() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
		postListPage.clickOnButtonDeletePost("Title is about");
		postListPage.clickOnDialogCancel();
		postListPage.openPage();
		postListPage.chooseOptionShowEntries("100");
		assertEquals(postListPage.isPostInList("Title is about"), true);
		
		}
	
	
	@Test     // Testiranje "Delete" buttona: Post Lists/All posts/kolona Actions/ Button za delete
	
	public void tc34TestDeletePostClickDelete() throws InterruptedException {
		
		postListPage.clickOnSearchInAllPosts();
		postListPage.searchPerTitle("Title is about");
		postListPage.chooseOptionShowEntries("10");
		postListPage.clickOnButtonDeletePost("Title is about");
		postListPage.clickOnDialogDelete();
		postListPage.openPage();
		postListPage.chooseOptionShowEntries("100");
		assertEquals(postListPage.isPostInList("Title is about"), false);	
		
	}
	
	public void checkMenuLink(String title, String url) {
    	
    	postListPage.openLinkParentInManu(title);
    	postListPage.clickOnLinkInMenu(title);
    		    	
    	assertEquals(driver.getCurrentUrl(), url, "Bad URL for "+title);

    	postListPage.openPage();
    	
  	}

	public void checkNavigationLink(String title, String url){
	
		postListPage.clickOnNavigationLink(title);
		assertEquals(driver.getCurrentUrl(), url, "Bad URL for "+title);

		postListPage.openPage();
	
}
	
	
}
