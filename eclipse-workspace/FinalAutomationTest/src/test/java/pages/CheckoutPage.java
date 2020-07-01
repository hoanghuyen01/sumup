package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class CheckoutPage extends BasePage{
	String SHIPPING_METHODS = "//table[@class = 'table-checkout-shipping-method']//td[text() = 'Table Rate']";
	String NEXT_BUTTON_NEXT = "//button[.//span[text()= 'Next']]";
	String BANK_TRANSFER_PAYMENT = "//span[text()='Bank Transfer Payment' ]";
	String PLACE_ORDER_BANK_TRANSFER_PAYMENT = "//div[@class = 'payment-method _active'][.//span[text()='Bank Transfer Payment' ]]//span[text()= 'Place Order']";
	String NUMBER_ORDER = "//a[@class = 'order-number']";
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void checkShippingMethodTableRate() {
		actionUtility.click(By.xpath(SHIPPING_METHODS));	
		}
	public void clickNextButton() {
		actionUtility.click(By.xpath(NEXT_BUTTON_NEXT));
	}
	public void checkPaymentMethod() {
		actionUtility.click(By.xpath(BANK_TRANSFER_PAYMENT));
		actionUtility.click(By.xpath(PLACE_ORDER_BANK_TRANSFER_PAYMENT));
	}
	public boolean checkNumberOrder() {
		if (actionUtility.checkElementExist(By.xpath(NUMBER_ORDER), 6)) {
			actionUtility.click(By.xpath(NUMBER_ORDER));
			return true;
		}
		return false;
	}

}
