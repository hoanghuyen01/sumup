package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;

import base.BasePage;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

	String XPATH_LINK_SIGN_IN = "//div[@class='panel header']//li[@class='authorization-link']/a";
	String XPATH_LINK_SIGN_UP = "//div[@class='panel header']//a[text() = \"Create an Account\"]";
	String XPATH_LOGGED_IN = "//div[@class='panel header']//span[@class='logged-in']";
	String ID_SEARCH_INPUT = "search";
	String ID_SEARCH_ICON ="//button[@title = 'Search']";
	String MESSAGE_NO_RESULTS ="//div[@class = 'message notice']";
	String LIST_PRODUCT_SEARCH = "//ol[@class = 'products list items product-items']";
	String NAME_FIRST_ITEMS_RESULT_SEARCH = "(//a[@class = 'product-item-link'])[1]";
	//menu (Men >Tops >Tees)
	String ID_MEN_CATEGORY = "ui-id-5";
	String ID_MEN_TOPS_CATEGORY = "ui-id-18";
	String ID_MEN_TEES_CATEGORY = "ui-id-22";
	String HEADER_TITLE_CATEGORY = "//h1[@id = 'page-title-heading']//span";
	//menu (Women>Tops >Tees)
	String ID_WOMEN_CATEGORY = "ui-id-5";
	String ID_WOMEN_TOPS_CATEGORY = "ui-id-10";
	String ID_WOMEN_TEES_CATEGORY = "ui-id-14";
	
	String XPATH_IMAGE_PRODUCT = "//img[@class = 'product-image-photo'][@alt = '%s']";
	 String XPATH_TITLE_PAGE ="//span[@class = 'base']";
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage open() {
		driver.get("https://demo.smartosc.com/");
		return this;
	}
	@Step("click on Sign in Link on homepage")
	public HomePage clickOnSignIn() {
		actionUtility.click(By.xpath(XPATH_LINK_SIGN_IN));
		return this;
	}
	@Step("click on sign up on homePage")
	public HomePage clickOnSignUp() {
		actionUtility.click(By.xpath(XPATH_LINK_SIGN_UP));
		return this;
	}
	public boolean checkUserLoggedIn() {
		return actionUtility.checkElementDisplay(By.xpath(XPATH_LOGGED_IN));
	}
	public void searchProduct(String data) {
		actionUtility.sendKeys(By.id(ID_SEARCH_INPUT), data);
		actionUtility.click(By.xpath(ID_SEARCH_ICON));
	}
	public boolean haveNoProductOfSearchResults() {
		if (actionUtility.checkElementExist(By.xpath(MESSAGE_NO_RESULTS), 6)) {
			return true;
		}
		return true;
	}
	public String getFirstResultSearchProductName() {
		waitUtility.sleep(2);
		if (actionUtility.checkElementExist(By.xpath(LIST_PRODUCT_SEARCH), 6)) {
			return actionUtility.getText(By.xpath(NAME_FIRST_ITEMS_RESULT_SEARCH));
		}
		return "";
	}
	public void selectCategoryOnMenu() {
		waitUtility.sleep(6);
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.id(ID_MEN_CATEGORY));
		actions.moveToElement(mainMenu);
		WebElement subMenu = driver.findElement(By.id(ID_MEN_TOPS_CATEGORY));
		actions.moveToElement(subMenu);
		WebElement childMenu = driver.findElement(By.id(ID_MEN_TEES_CATEGORY));
		actions.moveToElement(childMenu);
		actions.click().build().perform();
	}
	public String getTitleOfCategory() {
		return actionUtility.getText(By.xpath(HEADER_TITLE_CATEGORY));
	}
	public void clickOnImageProduct(String nameProduct) {
		String urlImageProduct = String.format(XPATH_IMAGE_PRODUCT, nameProduct);
		actionUtility.click(By.xpath(urlImageProduct));
	}
	public String getTitlePage() {
		return actionUtility.getText(By.xpath(XPATH_TITLE_PAGE));
	}
}
