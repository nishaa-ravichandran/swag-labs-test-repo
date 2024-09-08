package testScripts;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.LoginPage;
import pages.SwagLabsPage;
import utils.DriverManager;
import utils.SwagsReusableLibrary;

public class SwagsTest {

	static WebDriver driver = DriverManager.getWebDriver();
	static LoginPage loginPage = new LoginPage();
	static SwagLabsPage swagLabsPage = new SwagLabsPage();

	public static void testLockedOutUser() {
		try {
			String error = loginPage.loginError.getText().trim();
			Assert.assertEquals(error, "Epic sadface: Sorry, this user has been locked out.", "The User Could not login");
//			if(error.equals("Epic sadface: Sorry, this user has been locked out.")) {
//				System.out.println("FAIL: The User Could not login");
//				Assert.assertTrue(true, "The User Could not login as the user is locked out");
//			}
//			else {
//				System.out.println("FAIL: Locked Out User Could login");
//				Assert.assertTrue(false, "The User Could not login");
//			}
		}
		catch(Exception ex) {}
	}
	
	public static void testCheckoutFunctionality(){
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(swagLabsPage.pageHeader));
			System.out.println("PASS: The logged in user has a performance glitch");
		}
		catch(Exception ex) {
			System.out.println("FAIL: The logged in user has a performance glitch");
			Assert.assertTrue(false, "The logged in user has a performance glitch");
		}
		
		String[] itemsToAdd = {"Sauce Labs Bike Light", "Sauce Labs Backpack"};
		driver.findElement(By.xpath("//*[text()=\""+itemsToAdd[0]+"\"]/../../following-sibling::div/button")).click();
		driver.findElement(By.xpath("//*[text()=\""+itemsToAdd[1]+"\"]/../../following-sibling::div/button")).click();
		swagLabsPage.addToCartLink.click();
		
		List<WebElement> cartElements = swagLabsPage.cartElementsList;
		int foundFlag = 0;
		for(WebElement ele : cartElements) {
			for(String str : itemsToAdd) {
				if(str.equals(ele.getText())) {
					foundFlag++;
					break;
				}	
			}
		}
		Assert.assertEquals(foundFlag, 2, "Items not added to the cart");
		System.out.println("PASS: Items added to the cart successfully");
//		if(foundFlag==2) {
//			System.out.println("PASS: Items added to the cart successfully");
//			Assert.assertTrue(true, "Items added to the cart successfully");
//		}
//		else {
//			System.out.println("FAIL: Items not added to the cart");
//			Assert.assertTrue(false, "Items not added to the cart");
//		}

		swagLabsPage.checkOutButton.click();
		swagLabsPage.firstNameInput.sendKeys("Nishaa");
		swagLabsPage.lastNameInput.sendKeys("Ravichandran");
		swagLabsPage.postalCodeInput.sendKeys("123456");
		swagLabsPage.continueButton.click();
		try {
			swagLabsPage.finishButton.click();
			}
		catch(Exception ex) {
			System.out.println("FAIL: The User is problematic");
			Assert.assertTrue(false, "The User is problematic");
		}
		try {
			String completeMessage = swagLabsPage.orderSuccessMessage.getText();
			Assert.assertEquals(completeMessage, "Thank you for your order!", "Checkout Failed!!");
			System.out.println("Order Placed Successfully!!");
//			if(completeMessage.equals("Thank you for your order!"))
//				Assert.assertTrue(true, "Order Placed Successfully!!");
//			else {
//				System.out.println("FAILED: Checkout Failed!!");
//				Assert.assertTrue(false, "Checkout Failed!!");
//			}
		}
		catch(Exception ex) {
			System.out.println("FAILED: The user is error_user and could not finish the checkout");
			Assert.assertTrue(false, "The user is error_user and could not finish the checkout");
		}
	}

	public static void testSortingFunctionality() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(swagLabsPage.pageHeader));
			Assert.assertTrue(true, "User Logged In successfully");
		}
		catch(Exception ex) {
		}

		//Items Sorting
		List<String> initialItemList = new ArrayList<>() ;
		List<WebElement> itemElementList = swagLabsPage.itemElementList;
		for(WebElement ele: itemElementList) 
			initialItemList.add(ele.getText().trim());

		WebElement dropDown = swagLabsPage.dropDownOption;
		Select select = new Select(dropDown);
		select.selectByVisibleText("Name (Z to A)");

		List<String> descSortedItemList = new ArrayList<>() ;
		List<WebElement> descSortedItemElementList = swagLabsPage.itemElementList;;
		for(WebElement ele: descSortedItemElementList) 
			descSortedItemList.add(ele.getText().trim());

		Collections.sort(initialItemList);
		Collections.reverse(initialItemList);
		
		Assert.assertEquals(initialItemList, descSortedItemList, "Items not sorted in Z to A order!!");
		System.out.println("Items sorted in Z to A successfully!!");
//		if(initialItemList.equals(descSortedItemList)) {
//			System.out.println("Items sorted in Z to A successfully!!");
//			Assert.assertTrue(true, "Items sorted in Z to A successfully!!");
//		}
//			else {
//			System.out.println("Items not sorted in Z to A order!!");
//			Assert.assertTrue(false, "Items not sorted in Z to A order!!");
//		}
		dropDown = swagLabsPage.dropDownOption;
		select = new Select(dropDown);
		select.selectByVisibleText("Name (A to Z)");
		List<String> ascSortedItemList = new ArrayList<>() ;
		List<WebElement> ascSortedElementList = swagLabsPage.itemElementList;
		for(WebElement ele: ascSortedElementList) 
			ascSortedItemList.add(ele.getText().trim());

		Collections.sort(initialItemList);
		
		Assert.assertEquals(initialItemList, ascSortedItemList, "Items not sorted in A to Z order!!");
		System.out.println("Items sorted in A to Z order successfully!!");
//		if(initialItemList.equals(ascSortedItemList)) {
//			System.out.println("Items sorted in A to Z order successfully!!");
//			Assert.assertTrue(true, "Items sorted in A to Z order successfully!!");
//		}
//		else {
//			System.out.println("Items not sorted in A to Z order!!");
//			Assert.assertTrue(true, "Items not sorted in A to Z order!!");
//		}

		//Price sorting
		List<Double> initialPriceList = new ArrayList<>() ;
		List<WebElement> initialPriceElementList = swagLabsPage.priceElementList;
		for(WebElement ele: initialPriceElementList) {
			initialPriceList.add(Double.parseDouble(ele.getText().trim().replace("$", "")));
		}

		dropDown = swagLabsPage.dropDownOption;
		select = new Select(dropDown);
		select.selectByVisibleText("Price (low to high)");

		List<Double> ascSortedPriceList = new ArrayList<>() ;
		List<WebElement> ascSortedPriceElementList = swagLabsPage.priceElementList;
		for(WebElement ele: ascSortedPriceElementList) 
			ascSortedPriceList.add(Double.parseDouble(ele.getText().trim().replace("$", "")));

		Collections.sort(initialPriceList);

		Assert.assertEquals(initialPriceList, ascSortedPriceList, "Items not sorted in Price Low to High order!!");
		System.out.println("Items sorted in Price Low to High order successfully!!");
//		if(initialPriceList.equals(ascSortedPriceList)) {
//			System.out.println("Items sorted in Price Low to High order successfully!!");
//			Assert.assertTrue(true, "Items sorted in Price Low to High order successfully!!");
//		}
//		else {
//			System.out.println("Items not sorted in Price Low to High order!!");
//			Assert.assertTrue(false, "Items not sorted in Price Low to High order!!");
//		}
		dropDown = swagLabsPage.dropDownOption;
		select = new Select(dropDown);
		select.selectByVisibleText("Price (high to low)");

		List<Double> descSortedPriceList = new ArrayList<>() ;
		List<WebElement> descSortedPriceElementList = swagLabsPage.priceElementList;
		for(WebElement ele: descSortedPriceElementList) 
			descSortedPriceList.add(Double.parseDouble(ele.getText().trim().replace("$", "")));

		Collections.reverse(initialPriceList);

		Assert.assertEquals(initialPriceList, descSortedPriceList, "Items not sorted in Price High to Low order!!");
		System.out.println("Items sorted in Price High to Low order successfully!!");
//		if(initialPriceList.equals(descSortedPriceList)) {
//			System.out.println("Items sorted in Price High to Low order successfully!!");
//			Assert.assertTrue(true, "Items sorted in Price High to Low order successfully!!");
//		}
//		else {
//			System.out.println("Items not sorted in Price High to Low order!!");
//			Assert.assertTrue(false, "Items not sorted in Price High to Low order!!");
//		}
	}
}
