package testRunner;

import utils.SwagsReusableLibrary;
import utils.GeneralReusableLibrary;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

import testScripts.SwagsTest;
public class testRunner {

	@BeforeMethod(groups = "standard")
	public void login() {
		SwagsReusableLibrary.loginToSwags("standard_user");
	}

	@Test(groups = "standard")
	public void testCheckoutFunctionality() {
		SwagsTest.testCheckoutFunctionality();
	}

	@Test(groups = "standard")
	public void testSortingFunctionality(){
		SwagsTest.testSortingFunctionality();
	}

	@Test(groups = "nonStandard",  dataProvider = "csv_data")	
	public void testLoginAsAllUsers(String name){
		SwagsReusableLibrary.loginToSwags(name);
		SwagsTest.testCheckoutFunctionality();
	}

	@Test(groups = "nonStandard")	
	public void testLockedOutUser(){
		SwagsReusableLibrary.loginToSwags("locked_out_user");
		SwagsTest.testLockedOutUser();
	}

	@AfterClass(groups = {"standard", "nonStandard"})	
	public void closeBrowser(){
		System.out.println("After Class");
		SwagsReusableLibrary.closeDriver();
	}

	@DataProvider (name = "csv_data")
	public Object[][] dpMethod() {
		try {
			return GeneralReusableLibrary.getDataFromCsv("src/test/java/resources/DataSheet.csv");
		} catch (IOException e) {
			return new Object[0][0];
		}
	}
}
