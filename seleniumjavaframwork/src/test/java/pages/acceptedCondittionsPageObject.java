package pages;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class acceptedCondittionsPageObject {
	WebDriver driver = null;


	public acceptedCondittionsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	By singnav = By.xpath("//a[@class='HeaderMenu-link no-underline mr-3']");
	By username = By.xpath("//input[@id='login_field']");
	By password = By.xpath("//input[@id='password']");
	By signInBtn = By.xpath("//input[@name='commit']");
	By searchBar = By.name("q");
	By allgithub = By.xpath("//span[@class='js-jump-to-badge-search-text-global']");
	By firstResult = By.xpath("//li[1]//div[1]//h3[1]//a[1]");
	By resultLabel = By.xpath("//h3[contains(text(),'121 repository results')]");


	public void allGitHubClick() {
		driver.findElement(allgithub).click();
	}

	public void navigateToSignin() {
		driver.findElement(singnav).click();

	}

	public void provideUserName(String text) {
		driver.findElement(username).sendKeys(text);
	}

	public void providePassWord(String text) {
		driver.findElement(password).sendKeys(text);
	}

	public void signInClick() {
		driver.findElement(signInBtn).click();
	}

	public String getPageTitle() {
		return	driver.getTitle();
	}

	public String getPlaceholderText() {
		return	driver.findElement(searchBar).getAttribute("data-unscoped-placeholder");	
	}

	public void searchAndSubbmit(String text) {
		driver.findElement(searchBar).sendKeys(text);
	}

	public void selectFirstResult() {
		driver.findElement(firstResult).click();
	}

	public String getLabelText() {
		return	driver.findElement(resultLabel).getText();
	}

	public String getResultUrl() {
		String element = driver.findElement(firstResult).getAttribute("data-hydro-click");
		JSONObject jsonObj=new JSONObject(element); 
		return jsonObj.getJSONObject("payload").getJSONObject("result").getString("url");
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1600)");
	}

	public void waitUntilAllGithubAppear() {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(allgithub));
	}

	public void waitResultLabelToAppear() {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultLabel));
	}


	public Boolean checkElementVisibability() {
		if(driver.findElement(By.xpath("//div[@id='readme']//a[1]//img[1]"))!= null){
			return true;
		}else{
			System.out.println("Element is Absent");
			return false;
		}

	}


}
