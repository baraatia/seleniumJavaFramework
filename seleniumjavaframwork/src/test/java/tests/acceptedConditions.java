package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.acceptedCondittionsPageObject;



public class acceptedConditions {

	private static WebDriver driver = null;
	public static String browserName = null;


	@BeforeSuite
	public static void setupWebDriver() {		
		WebDriverManager.chromedriver().version("2.36").setup();
		driver = new ChromeDriver();
	}

	@Test(priority=0)
	public static void displayProperPlaceholder() throws Exception {

		acceptedCondittionsPageObject pageobject = new acceptedCondittionsPageObject(driver);   

		driver.get("https://github.com/");

		Assert.assertTrue(pageobject.getPlaceholderText().equals("Search GitHub"));		
	}

	@Test(priority=1)
	public static void displayProperSearchResult() throws Exception {

		acceptedCondittionsPageObject pageobject = new acceptedCondittionsPageObject(driver);   

		displayProperPlaceholder();

		pageobject.searchAndSubbmit("python/cpython");

		pageobject.waitUntilAllGithubAppear();

		pageobject.allGitHubClick();

		pageobject.waitResultLabelToAppear();

		Assert.assertTrue(pageobject.getLabelText().equals("121 repository results"));						
	}


	@Test(priority=2)
	public static void displayProperUrlForSearchResult() throws Exception {

		acceptedCondittionsPageObject pageobject = new acceptedCondittionsPageObject(driver);   

		displayProperSearchResult();

		Assert.assertTrue(pageobject.getResultUrl().equals("https://github.com/python/cpython"));							
	}

	@Test(priority=3)
	public static void openTheSelectedRepo() throws Exception {

		acceptedCondittionsPageObject pageobject = new acceptedCondittionsPageObject(driver);   

		displayProperSearchResult();

		pageobject.selectFirstResult();

		Assert.assertTrue(driver.getTitle().equals("GitHub - python/cpython: The Python programming language"));		
	}

	@Test(priority=4)
	public static void checkingRepoDetails() throws Exception {

		acceptedCondittionsPageObject pageobject = new acceptedCondittionsPageObject(driver);   

		openTheSelectedRepo();

		pageobject.scrollDown();

		Assert.assertTrue(pageobject.checkElementVisibability().equals(true));		
	}

	@AfterSuite
	public void tearDownTest() {
		driver.close();
		driver.quit();
		System.out.println("Test completed");
	}

}

