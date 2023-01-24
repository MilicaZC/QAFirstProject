package webPages.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;
	private static final String PAGE_URL="http://testblog.kurs-qa.cubes.edu.rs/login";
	@FindBy(name="email")
	private WebElement weEmail;
	@FindBy(name="password")
	private WebElement wePassword;
	@FindBy(xpath="//button[@type ='submit']")
	private WebElement weButton;
	@FindBy(id="email-error")
	private WebElement weErrorEmail;
	@FindBy(id="password-error")
	private WebElement weErrorPassword;
	@FindBy(xpath="//div[@class='invalid-feedback']/strong")
	private WebElement weCredentialError;
	public LoginPage (WebDriver driver) {
		this.driver = driver;
		driver.get(PAGE_URL);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void loginSuccess() {
		 	    
		 weEmail.sendKeys("kursqa@cubes.edu.rs");
		 wePassword.sendKeys("cubesqa");
		 weButton.click();
		
	}
	
	public void inputStringInEmail(String email) {
		weEmail.clear();
		weEmail.sendKeys(email);
	}
	
	public void inputStringInPassword(String password) {
		wePassword.clear();
		wePassword.sendKeys(password);		
	}
	
	public void clickOnButtonSingIn() {
		weButton.click();
	}
	
	public void openPage() {
		driver.get(PAGE_URL);
	}
	
	public String getEmailErrorMessage() {
		return weErrorEmail.getText();
	}
	
	public String getPasswordErrorMessage() {
		return weErrorPassword.getText();
	}
	
	public String getCredentialErrorMessage() {
		return weCredentialError.getText();
		
	}
	
}
