package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.DriverManager;

public class LoginPage {
	
WebDriver driver = DriverManager.getWebDriver();

public LoginPage() {
	PageFactory.initElements(driver, this);
}

@FindBy(how = How.XPATH, using = "//input[@id='user-name']" )
public WebElement userNameInput;

@FindBy(how = How.XPATH, using = "//input[@id='password']" )
public WebElement passwordInput;

@FindBy(how = How.XPATH, using = "//input[@id='login-button']" )
public WebElement loginButton;

@FindBy(how = How.XPATH, using = "//*[@class='error-message-container error']" )
public WebElement loginError;
}
