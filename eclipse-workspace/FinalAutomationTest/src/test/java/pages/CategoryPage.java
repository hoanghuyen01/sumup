package pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;
import io.qameta.allure.Step;

public class CategoryPage extends BasePage {
	String XPATH_SORT_BY_PRICE = "(//select[@id = 'sorter'])[1]";
	String XPATH_ASC_SORTING = "(//a[@title = 'Set Ascending Direction'])[1]";
	String XPATH_DES_SORTING = "(//a[@title = 'Set Descending Direction'])[1]";
	String XPATH_PRODUCT_ITEM = "(//li[@class = 'item product product-item'])[%d]";
	String XPATH_PRODUCT_LIST_ITEM = "//li[@class='item product product-item']//span[@class='price']";
	String XPATH_PRODUCT_ITEM_PRICE = "(//li[@class='item product product-item']//span[@class='price'])[%s]";
	String XPATH_NEXT = "(//a[@title = 'Next'])[2]";
	String XPATH_ADD_TO_COMPARE = "//div[@class = 'product details product-item-details'][.//a[normalize-space(text())='%s']]//a[@class = 'action tocompare']";
	String XPATH_ADD_TO_WISHLIST = "//div[@class = 'product details product-item-details'][.//a[normalize-space(text())='%s']]//a[@class = 'action towishlist']";
	String XPATH_SIZE_S = "//div[@class = 'product details product-item-details'][.//a[normalize-space(text())='%s']]//div[text()= 'S']";
	String XPATH_COLOR_BLACK = "//div[@class = 'product details product-item-details'][.//a[normalize-space(text())='%s']]//div[@option-label= 'Black']";
	String XPATH_ADD_PRODUCT_TO_CART = "//div[@class = 'product details product-item-details'][.//a[normalize-space(text())='%s']]//button[@title= 'Add to Cart']";
	
	//combine filter note : color_black_id : 49; size_s_id :5595
	String XPATH_FILTER_SIZE = "//div[@class = 'filter-options-title'][text() = 'Size']";
	String XPATH_FILTER_PRICE = "//div[@class = 'filter-options-title'][text() = 'Price']";
	String XPATH_FILTER_COLOR = "//div[@class = 'filter-options-title'][text() = 'Color']";
	String XPATH_FILTER_PRICE_60_69 = "//li[@class = 'item']//span[contains(text(),'60,00')]";
	String XPATH_FILTER_COLOR_BLACK = "//div[@class = 'filter-options-content']//div[@option-label = 'Black']";
	String XPATH_FILTER_SIZE_S = "//div[@class = 'filter-options-content']//div[@option-label = 'S']";
	
	String ID_COMPARE_LIST = "compare-items";
	String XPATH_MESSAGE_SUCCESS_COMPARISON_LIST = "(//div[@data-bind = 'html: message.text'])[1]";
	String XPATH_PRODUCT_COMPARE_LIST = "//ol[@id = 'compare-items']//a[text()='%s']";
	String XPATH_PRODUCT_WISH_LIST = "//ol[@id = 'wishlist-sidebar']//a[@title='%s']";
	String IMG_PRODUCT_LIST = "//li[@class = 'item product product-item'][.//img[@alt='%s']]";
	public CategoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@Step("Open category page")
	public CategoryPage open() {
		driver.get("https://demo.smartosc.com/men/tops-men/jackets-men.html");
		return this;
	}
	@Step("Click on sort by price on category page")
	public void clickSortBy(String typeSortValue) {
		Select drpSorting = new Select(driver.findElement(By.xpath(XPATH_SORT_BY_PRICE)));
		drpSorting.selectByValue(typeSortValue);
	}
	@Step("Click on icon Sort Descending")
	public void clickDescendingSorting() {
		if(actionUtility.checkElementExist(By.xpath(XPATH_DES_SORTING), 6)) {
			waitUtility.sleep(6);
			actionUtility.click(By.xpath(XPATH_DES_SORTING));
		}
	}
	
	public float getPrice(By xpath) {
        return Float.parseFloat(driver.findElement(xpath).getText().replace("US$", "").replace(",", "."));
    }
	
	@Step("Click on icon Sort Ascending")
	public void clickAscendingSorting()
	{
		if (actionUtility.checkElementExist(By.xpath(XPATH_ASC_SORTING), 6)) {
			actionUtility.click(By.xpath(XPATH_ASC_SORTING));
		}
	}
	public LinkedList getPriceListOfCategory(LinkedList<Float> listPrice) {
		 List<WebElement> productItems = driver.findElements(By.xpath(XPATH_PRODUCT_LIST_ITEM));
		 for (int i = 1; i <= productItems.size(); i++) {
			 listPrice.add(getPrice(By.xpath(String.format(XPATH_PRODUCT_ITEM_PRICE, i))));
		}
		 if (actionUtility.checkElementExist(By.xpath(XPATH_NEXT), 10)) {
			 actionUtility.click(By.xpath(XPATH_NEXT));
			 getPriceListOfCategory(listPrice);
		}
		 return listPrice;
	}
	public boolean comparePriceDesc(LinkedList<Float> listPrices) {
		float previousNumber = 1000000000;
		for (Float listPrice : listPrices) {
			if(listPrice<=previousNumber) {
				previousNumber = listPrice;
			}else {
				return false;
			}
		}
		return true;
	}
	public boolean comparePriceAcs(LinkedList<Float> listPrices) {
		float min = 0;
		for (Float listPrice : listPrices) {
			if(listPrice>=min) {
				min = listPrice;
			}else {
				return false;
			}
		}
		return true;
	}
	@Step("Click on add to compare")
	public void clickAddToCompare(String nameProduct) {
		waitUtility.sleep(4);
		System.out.println(String.format(XPATH_ADD_TO_COMPARE, nameProduct));
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath(String.format(IMG_PRODUCT_LIST, nameProduct)));
		actions.moveToElement(mainMenu);
		WebElement addCompare = driver.findElement(By.xpath(String.format(XPATH_ADD_TO_COMPARE, nameProduct)));
		actions.moveToElement(addCompare).click().build().perform();
	}
	@Step("Click on add to wish list icon")
	public void clickAddToWishList(String nameProduct) {
		waitUtility.sleep(4);
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath(String.format(IMG_PRODUCT_LIST, nameProduct)));
		actions.moveToElement(mainMenu);
		WebElement addCompare = driver.findElement(By.xpath(String.format(XPATH_ADD_TO_WISHLIST, nameProduct)));
		actions.moveToElement(addCompare).click().build().perform();
	}
	@Step("Click on select icon S size")
	public void selectSSize(String nameProduct) {
		actionUtility.click(By.xpath(String.format(XPATH_SIZE_S, nameProduct)));
	}
	@Step("Click on select icon Black color size")
	public void selectBlackColor(String nameProduct) {
		actionUtility.click(By.xpath(String.format(XPATH_COLOR_BLACK, nameProduct)));
	}
	@Step("Click on icon add to cart")
	public void addToCart(String nameProduct) {
		waitUtility.sleep(6);
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath(String.format(IMG_PRODUCT_LIST, nameProduct)));
		actions.moveToElement(mainMenu);
		WebElement addCompare = driver.findElement(By.xpath(String.format(XPATH_ADD_PRODUCT_TO_CART, nameProduct)));
		actions.moveToElement(addCompare).click().build().perform();
	}
	@Step("Select filter black:color on the left site")
	public void filterColorBlack() {
		actionUtility.click(By.xpath(XPATH_FILTER_COLOR));
		actionUtility.click(By.xpath(XPATH_FILTER_COLOR_BLACK));
	}
	@Step("Select filter S: Size on the left site")
	public void filterSizeS() {
		actionUtility.click(By.xpath(XPATH_FILTER_SIZE));
		actionUtility.click(By.xpath(XPATH_FILTER_SIZE_S));
		
	}
	@Step("Select filter 60-69.99:Price on the left site")
	public void filterPrice6070() {
		actionUtility.click(By.xpath(XPATH_FILTER_PRICE));
		actionUtility.click(By.xpath(XPATH_FILTER_PRICE_60_69));
	}
	public String checkExistProductOnCompareList() {
		
		if (actionUtility.checkElementExist(By.xpath(XPATH_MESSAGE_SUCCESS_COMPARISON_LIST), 6) ) {
			return actionUtility.getText(By.xpath(XPATH_MESSAGE_SUCCESS_COMPARISON_LIST));
		}
		return "";
	}
	public String checkExistProductOnWishList() {
			if (actionUtility.checkElementExist(By.xpath(XPATH_MESSAGE_SUCCESS_COMPARISON_LIST), 6) ) {
				return actionUtility.getText(By.xpath(XPATH_MESSAGE_SUCCESS_COMPARISON_LIST));
			}
			return "";
		}
	public boolean nameProductOnWishList(String nameProduct) {
		if (actionUtility.checkElementExist(By.xpath(String.format(XPATH_PRODUCT_WISH_LIST, nameProduct)), 6)) {
			return true;
		}
		return false;
	}
	public boolean nameProductOnCompareList(String nameProduct) {
		if (actionUtility.checkElementExist(By.xpath(String.format(XPATH_PRODUCT_COMPARE_LIST, nameProduct)), 6)) {
			return true;
		}
		return false;
	}
	public String addSuccessToCart() {
		if (actionUtility.checkElementExist(By.xpath(XPATH_MESSAGE_SUCCESS_COMPARISON_LIST), 6) ) {
			return actionUtility.getText(By.xpath(XPATH_MESSAGE_SUCCESS_COMPARISON_LIST));
		}
		return "";
	}
	
}
