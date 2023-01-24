package tests;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import webPages.Login.LoginPage;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestLogin {
	
	private static ChromeDriver driver;
	private static LoginPage loginPage;
	
	private final String EMAIL_VALID="kursqa@cubes.edu.rs";
	private final String EMAIL_INVALID = "example@mail123.com";
	private final String EMAIL_INCORECT = "abc123"; 
	private final String PASSWORD_VALID = "cubesqa";
	private final String PASSWORD_INVALID = "abcabc";
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "/C:/Users/pc/Desktop/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void test03LoginWithEmptyFields(){
		loginPage.inputStringInEmail("");
		loginPage.inputStringInPassword("");
		loginPage.clickOnButtonSingIn();
		
	assertEquals(loginPage.getEmailErrorMessage(), "Email* is required field");
	assertEquals(loginPage.getPasswordErrorMessage(), "Password* is required field");
	}
	
	@Test 
	public void test04LoginWithIncorectEmail() {
		loginPage.inputStringInEmail(EMAIL_INCORECT);
		loginPage.inputStringInPassword("");
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Please, enter the valid Email address");
	//	assertEquals(loginPage.getPasswordErrorMessage(), "Password* is required field");
	}

	@Test 
	public void test05LoginWithInvalidEmail() {
		loginPage.inputStringInEmail(EMAIL_INVALID);
		loginPage.inputStringInPassword("");
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getPasswordErrorMessage(), "Password* is required field");
	}
	
	@Test 
	public void test06LoginWithValidEmail() {
		loginPage.inputStringInEmail(EMAIL_VALID);
		loginPage.inputStringInPassword("");
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getPasswordErrorMessage(), "Password* is required field");
	}
	
	@Test 
	public void test07LoginWithEmptyEmailField() {
		loginPage.inputStringInEmail("");
		loginPage.inputStringInPassword(PASSWORD_INVALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Email* is required field");
	}
	
	@Test 
	public void test08LoginWithEmptyEmailValidPassword() {
		loginPage.inputStringInEmail("");
		loginPage.inputStringInPassword(PASSWORD_VALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Email* is required field");
	}
	@Test 
	public void test09LoginWithIncorectEmailInvalidPassword() {
		loginPage.inputStringInEmail(EMAIL_INCORECT);
		loginPage.inputStringInPassword(PASSWORD_INVALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Please, enter the valid Email address");
	}
	
	@Test 
	public void test10LoginWithInvalidEmailInvalidPassword() {
		loginPage.inputStringInEmail(EMAIL_INVALID);
		loginPage.inputStringInPassword(PASSWORD_INVALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getCredentialErrorMessage(), "These credentials do not match our records.");
	}
	@Test 
	public void test11LoginWithValidEmailInvalidPassword() {
		loginPage.inputStringInEmail(EMAIL_VALID);
		loginPage.inputStringInPassword(PASSWORD_INVALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getCredentialErrorMessage(), "Those credentials do not match our records.");
	}
	@Test 
	public void test12LoginWithIncorectEmaiiValidPassword() {
		loginPage.inputStringInEmail(EMAIL_INCORECT);
		loginPage.inputStringInPassword(PASSWORD_VALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Please, enter the valid Email address");
	}
	@Test 
	public void test13LoginWithInvalidEmaiiValidPassword() {
		loginPage.inputStringInEmail(EMAIL_INVALID);
		loginPage.inputStringInPassword(PASSWORD_VALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Those credentials do not match our records.");
	}
	@Test 
	public void test14LoginWithValidEmaiiValidPassword() {
		loginPage.inputStringInEmail(EMAIL_VALID);
		loginPage.inputStringInPassword(PASSWORD_VALID);
		loginPage.clickOnButtonSingIn();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin");
	}
	
}
