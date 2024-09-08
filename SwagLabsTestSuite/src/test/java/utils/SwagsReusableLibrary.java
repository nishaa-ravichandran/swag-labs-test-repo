package utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;

import pages.LoginPage;

public class SwagsReusableLibrary {
	static LoginPage loginPage = new LoginPage();
	static WebDriver driver = DriverManager.getWebDriver();
	

	public static void loginToSwags(String username) {
		System.out.println(username);
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		loginPage.userNameInput.sendKeys(username);
		loginPage.passwordInput.sendKeys("secret_sauce");
		loginPage.loginButton.click();
	}
	
	public static void closeDriver() {
		driver.close();
	}

}
