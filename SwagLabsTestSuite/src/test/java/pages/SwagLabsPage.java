package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.DriverManager;

public class SwagLabsPage {
		
		WebDriver driver = DriverManager.getWebDriver();

		public SwagLabsPage() {
			PageFactory.initElements(driver, this);
		}

		public By pageHeader= By.xpath("//div[@class='app_logo']" );
		
		
		@FindBy(how = How.XPATH, using = "//div[@class='inventory_item_name']" )
		public List<WebElement> cartElementsList;
		
		@FindBy(how = How.XPATH, using = "//*[@id='shopping_cart_container']/a" )
		public WebElement addToCartLink;

		
		@FindBy(how = How.XPATH, using = "//button[@id='checkout']" )
		public WebElement checkOutButton;
		
		@FindBy(how = How.XPATH, using = "//input[@id='first-name']" )
		public WebElement firstNameInput;
		
		@FindBy(how = How.XPATH, using = "//input[@id='last-name']" )
		public WebElement lastNameInput;
		
		@FindBy(how = How.XPATH, using = "//input[@id='postal-code']" )
		public WebElement postalCodeInput;
		
		
		
		@FindBy(how = How.XPATH, using = "//input[@id='continue']" )
		public WebElement continueButton;
		
		@FindBy(how = How.XPATH, using = "//button[@id='finish']" )
		public WebElement finishButton;
		
		@FindBy(how = How.XPATH, using = "//h2[@class='complete-header']" )
		public WebElement orderSuccessMessage;
		
//		Sorting
		
		@FindBy(how = How.XPATH, using = "//div[@class='inventory_item_name ']" )
		public List<WebElement> itemElementList;
		
		@FindBy(how = How.XPATH, using = "//select[@class='product_sort_container']" )
		public WebElement dropDownOption;
		
		@FindBy(how = How.XPATH, using = "//div[@class='inventory_item_price']" )
		public List<WebElement> priceElementList;


}
