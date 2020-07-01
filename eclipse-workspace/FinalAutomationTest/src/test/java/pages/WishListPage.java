package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import base.BasePage;

public class WishListPage extends BasePage {
	String XPATH_ADD_TO_WISH_LIST = "//div[@class = 'product-social-links']//a[@class = 'action towishlist']";
	String MESSAGE_ADD_WISH_LIST_SUCCESS = "//div[@data-ui-id = 'message-success']";
	String XPATH_IMAGE_WISH_LIST = "//div[@class = 'products-grid wishlist']//img[@alt = '%s']";
	String XPATH_REMOVE = "//div[@class = 'products-grid wishlist']//div[@class ='product-item-info'][.//a[@title = '%s']]//a[@title = 'Remove Item']";
	String XPATH_MESSAGE_REMOVE_SUCCESS = "//div[@data-bind ='html: message.text' ]";
	String XPATH_BUTTON_UPDATE = "//button[@title = 'Update Wish List' ]";
	String XPATH_BUTTON_SHARE = "//button[@title = 'Share Wish List' ]";
	String XPATH_BUTTON_ADD_TO_CART = "//button[@title = 'Add All to Cart' ]";
	String XPATH_MESSAGE_EMPTY_WISH_LIST = "//div[@class = 'message info empty']";

	public WishListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void addProductToWishList() {
		waitUtility.sleep(2);
		actionUtility.click(By.xpath(XPATH_ADD_TO_WISH_LIST));
	}

	public String checkAddingProductWishList() {
		return actionUtility.getText(By.xpath(MESSAGE_ADD_WISH_LIST_SUCCESS));
	}

	public String clickRemoveWishList(String nameProduct) {
		String image = String.format(XPATH_IMAGE_WISH_LIST, nameProduct);
		waitUtility.waitUntilExist(By.xpath(image), 6);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(image));
		action.moveToElement(we);
		String locatorRemoveButton = String.format(XPATH_REMOVE, nameProduct);
		WebElement removeButton = driver.findElement(By.xpath(locatorRemoveButton));
		action.moveToElement(removeButton).click().build().perform();
		return actionUtility.getText(By.xpath(XPATH_MESSAGE_REMOVE_SUCCESS));
	}
	public String checkExistOfButton() {
		if (actionUtility.checkElementExist(By.xpath(XPATH_BUTTON_UPDATE), 6)&& 
				actionUtility.checkElementExist(By.xpath(XPATH_BUTTON_SHARE), 6)&& 
				actionUtility.checkElementExist(By.xpath(XPATH_BUTTON_ADD_TO_CART), 6))
		{
			return "Have";
		}
		return "Have not";
	}
	public String checkExistProduct() {
		if (actionUtility.checkElementExist(By.xpath(XPATH_MESSAGE_EMPTY_WISH_LIST), 6)) {
			return "Have not";
		}
		return "Have";
	}

}
