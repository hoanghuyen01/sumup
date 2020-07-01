package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class SignUpPage extends BasePage {
	String ID_FIRSTNAME = "firstname";
	String ID_FIRSTNAME_ERROR = "firstname-error";
	String ID_LASTNAME = "lastname";
	String ID_LASTNAME_ERROR = "lastname-error";
	String ID_ADDRESS_EMAIL = "email_address";
	String ID_ADDRESS_EMAIL_ERROR = "email_address-error";
	String ID_PASSWORD = "password";
	String ID_PASSWORD_ERROR = "password-error";
	String ID_CONFIRM_PASSWORD = "password-confirmation";
	String ID_CONFIRM_PASSWORD_ERROR = "password-confirmation-error";
	String BUTTON_CREATE_ACCOUNT = "//button[@class = 'action submit primary']";
	
	public SignUpPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public SignUpPage open() {
		driver.get("https://demo.smartosc.com/customer/account/create/");
		return this;
	}
	public void fillDataForm(String firstName,String lastName,String email,String pass,String confirmPass) {
		actionUtility.sendKeys(By.id(ID_FIRSTNAME), firstName);
		actionUtility.sendKeys(By.id(ID_LASTNAME), lastName);
		actionUtility.sendKeys(By.id(ID_ADDRESS_EMAIL), email);
		actionUtility.sendKeys(By.id(ID_PASSWORD), pass);
		actionUtility.sendKeys(By.id(ID_CONFIRM_PASSWORD), confirmPass);
		waitUtility.sleep(3);
		actionUtility.click(By.xpath(BUTTON_CREATE_ACCOUNT));
	}
	public  String requireFirstName() {
		if (actionUtility.checkElementExist(By.id(ID_FIRSTNAME_ERROR), 6)) {
			return actionUtility.getText(By.id(ID_FIRSTNAME_ERROR));
		}
		return "";
	}
	public  String requireLastName() {
		if (actionUtility.checkElementExist(By.id(ID_LASTNAME_ERROR), 6)) {
			return actionUtility.getText(By.id(ID_LASTNAME_ERROR));
		}
		return "";
	}
	public  String requireEmail() {
		if (actionUtility.checkElementExist(By.id(ID_ADDRESS_EMAIL_ERROR), 6)) {
			return actionUtility.getText(By.id(ID_ADDRESS_EMAIL_ERROR));
		}
		return "";
	}
	public  String requirePassword() {
		if (actionUtility.checkElementExist(By.id(ID_PASSWORD_ERROR), 6)) {
			return actionUtility.getText(By.id(ID_PASSWORD_ERROR));
		}
		return "";
	}
	public  String requireConfirmPassword() {
		if (actionUtility.checkElementExist(By.id(ID_CONFIRM_PASSWORD_ERROR), 6)) {
			return actionUtility.getText(By.id(ID_CONFIRM_PASSWORD_ERROR));
		}
		return "";
	}
}
