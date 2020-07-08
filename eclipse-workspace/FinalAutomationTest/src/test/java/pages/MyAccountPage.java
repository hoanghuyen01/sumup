package pages;

import org.jsoup.select.Evaluator.IsEmpty;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;
import io.qameta.allure.Step;

public class MyAccountPage extends BasePage {
	String CHANGE_PASS = "//a[@class = 'action change-password']";
	String ID_CURRENT_PASS = "current-password";
	String ID_NEW_PASS = "password";
	String ID_CONFIRM_PASS = "password-confirmation";
	String XPATH_SAVE_EDIT_ACCOUNT_INFOR = "//button[@class = 'action save primary']";
	String ID_CONFIRM_PASS_ERROR ="password-confirmation-error";
	String XPATH_EDIT_BILLING_ADDRESS = "//a[@data-ui-id='default-billing-edit-link']";
	//input contact add new address
	String ID_FIRST_NAME = "firstname";
	String ID_LAST_NAME = "lastname";
	String ID_COMPANY = "company";
	String ID_STREET_ADDRESS = "street_1";
	String ID_CITY = "city";
	String ID_TELEPHONE = "telephone";
	String ID_POSTCODE = "zip";
	String ID_REGION_SELECT = "region_id";
	String ID_REGION = "region";
	String ID_COUNTRY = "country";
	String ID_PHONE = "telephone";
	String XPATH_BUTTON_SAVE_ADDRESS = "//button[@title = 'Save Address']";
	// message error change billing address
	String ERROR_ID_FIRST_NAME = "firstname-error";
	String ERROR_ID_LAST_NAME = "lastname-error";
	String ERROR_ID_TELEPHONE ="telephone-error";
	String ERROR_ID_STREET_ADDRESS ="street_1-error";
	String ERROR_ID_POSTCODE ="zip-error";
	String ERROR_ID_COUNTRY ="country-error";
	String ERROR_ID_CITY = "city-error";
	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	String MESSAGE_ERROR_ACOUNT = "//div[@data-bind='html: message.text']";
	@Step("Click on password on my account page")
	public void clickChangePasswordLink() {
		actionUtility.click(By.xpath(CHANGE_PASS));
	}
	
	@Step("Input current password, newPassWord and confirm PassWord")
	public void changePassword(String currentPass, String newPass, String confirmPass) {
		actionUtility.sendKeys(By.id(ID_CURRENT_PASS), currentPass);
		actionUtility.sendKeys(By.id(ID_NEW_PASS), newPass);
		actionUtility.sendKeys(By.id(ID_CONFIRM_PASS), confirmPass);
		actionUtility.click(By.xpath(XPATH_SAVE_EDIT_ACCOUNT_INFOR));
	}
	public String checkInputWrongPass() {
		if (actionUtility.checkElementExist(By.xpath(MESSAGE_ERROR_ACOUNT), 6)) {
			return actionUtility.getText(By.xpath(MESSAGE_ERROR_ACOUNT));
		}
		return "";
	}
	public String checkInputWrongConfirmPass() {
		if (actionUtility.checkElementExist(By.id(ID_CONFIRM_PASS_ERROR), 6)) {
			return actionUtility.getText(By.id(ID_CONFIRM_PASS_ERROR));
		}
		return "";
	}
	public void clickOnChangeAddressBilling() {
		actionUtility.click(By.xpath(XPATH_EDIT_BILLING_ADDRESS));
	}
	public void editBillingAddress(String firstName, String lastName, String company, String phoneNumber,String streetAddress,
			String city, String region,String postcode,String country) {
		waitUtility.sleep(2);
		String firstNameCurrent = actionUtility.getTextField(By.id(ID_FIRST_NAME));
		if (!firstNameCurrent.isEmpty()) {
			actionUtility.removeInput(By.id(ID_FIRST_NAME));
			actionUtility.removeInput(By.id(ID_LAST_NAME));
		}
//		Select drpRegion = new Select(driver.findElement(By.id(ID_REGION_SELECT)));
//		drpRegion.selectByValue(region);
		Select drpCountry = new Select(driver.findElement(By.id(ID_COUNTRY)));
		drpCountry.selectByValue(country);
		actionUtility.sendKeys(By.id(ID_FIRST_NAME), firstName);
		actionUtility.sendKeys(By.id(ID_LAST_NAME), lastName);
		actionUtility.removeInput(By.id(ID_COMPANY));
		actionUtility.sendKeys(By.id(ID_COMPANY), company);
		actionUtility.removeInput(By.id(ID_STREET_ADDRESS));
		actionUtility.sendKeys(By.id(ID_STREET_ADDRESS), streetAddress);
		actionUtility.removeInput(By.id(ID_PHONE));
		actionUtility.sendKeys(By.id(ID_PHONE), phoneNumber);
		actionUtility.removeInput(By.id(ID_CITY));
		actionUtility.sendKeys(By.id(ID_CITY), city);
		actionUtility.removeInput(By.id(ID_REGION));
		actionUtility.sendKeys(By.id(ID_REGION), region);
		actionUtility.removeInput(By.id(ID_POSTCODE));
		actionUtility.sendKeys(By.id(ID_POSTCODE), postcode);
		actionUtility.click(By.xpath(XPATH_BUTTON_SAVE_ADDRESS));
	}
	public String requireFirstName() {
		if (actionUtility.checkElementExist(By.id(ERROR_ID_FIRST_NAME), 6)) {
			return actionUtility.getText(By.id(ERROR_ID_FIRST_NAME));
		}
		return "";		
	}
	public String requireLastName() {
		if (actionUtility.checkElementExist(By.id(ERROR_ID_LAST_NAME), 6)) {
			return actionUtility.getText(By.id(ERROR_ID_LAST_NAME));
		}
		return "";		
	}
	public String requirePhoneNumber() {
		if (actionUtility.checkElementExist(By.id(ERROR_ID_TELEPHONE), 6)) {
			return actionUtility.getText(By.id(ERROR_ID_TELEPHONE));
		}
		return "";		
	}
	public String requireStreet() {
		if (actionUtility.checkElementExist(By.id(ERROR_ID_STREET_ADDRESS), 6)) {
			return actionUtility.getText(By.id(ERROR_ID_STREET_ADDRESS));
		}
		return "";		
	}
	public String requireCity() {
		if (actionUtility.checkElementExist(By.id(ERROR_ID_CITY), 6)) {
			return actionUtility.getText(By.id(ERROR_ID_CITY));
		}
		return "";		
	}
	public String requirePostCode() {
		if (actionUtility.checkElementExist(By.id(ERROR_ID_POSTCODE), 6)) {
			return actionUtility.getText(By.id(ERROR_ID_POSTCODE));
		}
		return "";		
	}
	public String requireCountry() {
		if (actionUtility.checkElementExist(By.id(ERROR_ID_COUNTRY), 6)) {
			return actionUtility.getText(By.id(ERROR_ID_COUNTRY));
		}
		return "";		
	}
}
