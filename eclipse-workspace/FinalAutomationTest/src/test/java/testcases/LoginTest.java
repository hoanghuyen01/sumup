package testcases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class LoginTest extends BaseTest {
	String EMPTY_MESSAGE = "This is a required field.";
	String INVALID_EMAIL = "Please enter a valid email address (Ex: johndoe@domain.com).";
	String MESSAGE_ERROR_ACOUNT = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
	HomePage homePage;
	SignInPage signInPage;
	
	@BeforeTest
	public void data() {
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
	}
	public boolean checkFormartEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(email);
		return matcher.matches();
	}
	public boolean checkDataLogin(String email, String pass) {
		if(email == "" || pass == "") {
			return false;
		}else if (!checkFormartEmail(email)) {
			return false;
		}
		return true;
	}
	/**
	 * @param email
	 * @param pass
	 * check the email field with cases:
	 * email input is empty
	 * email input is invalid
	 */
	@Test(dataProvider = "data_test_email")
	public void checkEmail(String email,String pass) {
		homePage.open().clickOnSignIn();
		signInPage.login(email, pass);
		if (email == "") {
			Assert.assertEquals(signInPage.validEmail(), EMPTY_MESSAGE);
		}else if(!checkFormartEmail(email)) {
			Assert.assertEquals(signInPage.validEmail(), INVALID_EMAIL);
		}	
	}
	/**
	 * @param email
	 * @param pass
	 * check Account with precondition : 
	 * 		email is not empty and valid
	 * 		pass is not empty
	 */
	@Test(dataProvider = "data_test_invalid_Account")
	public void checkInvalidAccount(String email, String pass) {
		homePage.open().clickOnSignIn();
		signInPage.login(email, pass);
		if(checkDataLogin(email, pass)) {
			Assert.assertEquals(signInPage.invalidAccount(),MESSAGE_ERROR_ACOUNT);
		}
	}
	@Test(dataProvider = "data_test_valid_Account")
	public void checkValidAccount(String email, String pass) {
		homePage.open().clickOnSignIn();
		signInPage.login(email, pass);
		Assert.assertEquals(signInPage.existWelcomeButton(),true);
	}
	@DataProvider(name = "data_test_email")
	public static Object[][] dataEmailTest(){
		return new Object[][] {{"","Huyen01$"}, {"huyenhoang","Huyen01$"}};
	}
	@DataProvider(name = "data_test_invalid_Account")
	public static Object[][] dataInvalidAccountTest(){
		return new Object[][] {{"phuonghoang@gmail.com","Huyen01$"}};
	}
	@DataProvider(name = "data_test_valid_Account")
	public static Object[][] dataValidAccountTest(){
		return new Object[][] {{"huyenhoang@gmail.com","Huyen01$"}};
	}

}
