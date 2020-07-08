package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import io.qameta.allure.Step;

public class SignInPage extends BasePage {

	String ID_INPUT_EMAIL = "email";
	String ID_INPUT_PASSWORD = "pass";
	String ID_BUTTON_SIGN_IN = "send2";
	String ID_ERROR_INPUT_EMAIL ="email-error";
	String MESSAGE_ERROR_ACOUNT = "//div[@data-bind='html: message.text']";
	String WELCOME_BUTTON = "//div[@class='panel header']//button[@class='action switch']";
	String LOGOUT_BUTTON = "(//a[normalize-space(text())='Sign Out'])[1]";
	
	public SignInPage(WebDriver driver) {
		super(driver);
	}
	@Step("Login with {email}/{pass}")
	public void login(String email, String pass) {
		actionUtility.sendKeys(By.id(ID_INPUT_EMAIL), email);
		actionUtility.sendKeys(By.id(ID_INPUT_PASSWORD), pass);
		waitUtility.sleep(6);
		actionUtility.click(By.id(ID_BUTTON_SIGN_IN));
	}
	public String validEmail() {
		waitUtility.waitUntilExist(By.id(ID_ERROR_INPUT_EMAIL),6);
		if(actionUtility.checkElementExist(By.id(ID_ERROR_INPUT_EMAIL),6)) {
			return actionUtility.getText(By.id(ID_ERROR_INPUT_EMAIL));
		}
		return "";
	}
	public String invalidAccount() {
		if(actionUtility.checkElementExist(By.xpath(MESSAGE_ERROR_ACOUNT),6)) {
			return actionUtility.getText(By.xpath(MESSAGE_ERROR_ACOUNT));
		}
		return "";
	}
	public boolean existWelcomeButton() {
		if (actionUtility.checkElementExist(By.xpath(WELCOME_BUTTON),6)) {
			return true;
		}
		return false;
		
	}
	public boolean checkout() {
		actionUtility.click(By.xpath(WELCOME_BUTTON));
		actionUtility.click(By.xpath(LOGOUT_BUTTON));
		if (existWelcomeButton()) {
			return false;
		}
		return true;
	}
}
