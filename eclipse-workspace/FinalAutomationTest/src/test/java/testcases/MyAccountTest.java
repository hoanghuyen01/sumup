package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pages.HomePage;
import pages.MyAccountPage;
import pages.SignInPage;

public class MyAccountTest extends BaseTest{
	String MESSAGE_ERROR_CURRENT_PASS ="The password doesn't match this account. Verify the password and try again.";
	String MESSAGE_ERROR_CONFIRM_PASS = "Please enter the same value again.";
	String MESSAGE_ERROR_REQUIRE_INPUT_BILLING_ADDRESS = "This is a required field.";
	String MESSAGE_ERROR_REQUIRE_SELECT_BILLING_ADDRESS = "Please select an option.";
	String URL_MY_ACCOUNT = "https://demo.smartosc.com/customer/account/";
	MyAccountPage myAccountPage;
	HomePage homePage;
	SignInPage signInPage;
	@BeforeTest
	public void data() {
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
		myAccountPage = new MyAccountPage(driver);
	}
	@Test(dataProvider = "invalid_current_pass")
	@Description("Test invalid current password")
	public void testSendInvailCurrentPass(String email, String pass,String wrongPass ,String newPass, String confirmPass) {
		homePage.open();
		if (!signInPage.existWelcomeButton()) {
			homePage.clickOnSignIn();
			signInPage.login(email, pass);
		}
		if (driver.getCurrentUrl() != URL_MY_ACCOUNT ) {
			driver.get(URL_MY_ACCOUNT);
		}
		myAccountPage.clickChangePasswordLink();
		myAccountPage.changePassword(wrongPass, newPass, confirmPass);
		Assert.assertEquals(myAccountPage.checkInputWrongPass(),MESSAGE_ERROR_CURRENT_PASS,"Test wrong current password pass!");
		
	}
	@Test(dataProvider = "invalid_confirm_pass")
	@Description("Test invalid confirm password")
	public void testSendInvailConfirmPass(String email, String pass ,String newPass, String confirmPass) {
		homePage.open();
		if (!signInPage.existWelcomeButton()) {
			homePage.clickOnSignIn();
			signInPage.login(email, pass);
		}
		if (driver.getCurrentUrl() != URL_MY_ACCOUNT ) {
			driver.get(URL_MY_ACCOUNT);
		}
		myAccountPage.clickChangePasswordLink();
		myAccountPage.changePassword(pass, newPass, confirmPass);
		Assert.assertEquals(myAccountPage.checkInputWrongConfirmPass(),MESSAGE_ERROR_CONFIRM_PASS,"Test invalid confirm password pass");
	}
	@Test(dataProvider = "data_change_billing_address")
	public void changeBillingaddress(String email, String pass ,String firstName, String lastName, String company, String phoneNumber,String streetAddress,
			String city, String region,String postcode,String country) {
		homePage.open();
		if (!signInPage.existWelcomeButton()) {
			homePage.clickOnSignIn();
			signInPage.login(email, pass);
		}
		if (driver.getCurrentUrl() != URL_MY_ACCOUNT ) {
			driver.get(URL_MY_ACCOUNT);
		}
		myAccountPage.clickOnChangeAddressBilling();
		myAccountPage.editBillingAddress(firstName, lastName, company, phoneNumber, streetAddress, city, region, postcode, country);
		if (firstName =="") {
			Assert.assertEquals(myAccountPage.requireFirstName(),MESSAGE_ERROR_REQUIRE_INPUT_BILLING_ADDRESS,"Test require first name passed");
			captureScreenshot("firstname");
		}else if (lastName == "") {
			Assert.assertEquals(myAccountPage.requireLastName(),MESSAGE_ERROR_REQUIRE_INPUT_BILLING_ADDRESS,"Test require last name passed ");
			captureScreenshot("lastname");
		}else if (phoneNumber== "") {
			Assert.assertEquals(myAccountPage.requirePhoneNumber(),MESSAGE_ERROR_REQUIRE_INPUT_BILLING_ADDRESS,"Test require phone number passed");
			captureScreenshot("phonenumber");
		}else if (streetAddress =="") {
			Assert.assertEquals(myAccountPage.requireStreet(),MESSAGE_ERROR_REQUIRE_INPUT_BILLING_ADDRESS,"Test require Street passed");
			captureScreenshot("streetaddress");
		}else if (city == "") {
			Assert.assertEquals(myAccountPage.requireCity(),MESSAGE_ERROR_REQUIRE_INPUT_BILLING_ADDRESS,"Test require City passed");
		}else if (country == "") {
			Assert.assertEquals(myAccountPage.requireCountry(),MESSAGE_ERROR_REQUIRE_SELECT_BILLING_ADDRESS,"Test require country passed");
		}else if (postcode == "") {
			Assert.assertEquals(myAccountPage.requirePostCode(),MESSAGE_ERROR_REQUIRE_INPUT_BILLING_ADDRESS,"Test require PostCode passed");
		}
	}
	
	@DataProvider(name = "data_change_billing_address")
	public static Object[][] dataChangeInformation(){
		return new Object[][] {
			{"huyenhoang@gmail.com","Huyen01$","","Le","SmartOsc", "0317549393" ,"Thanh Xuan, Nguyen Trai","Ha Noi","Ha Noi","1000","VN"},
			{"huyenhoang@gmail.com","Huyen01$","Phuong","","SmartOsc", "0317549393" ,"Thanh Xuan, Nguyen Trai","Ha Noi","Ha Noi","1000","VN"},
			{"huyenhoang@gmail.com","Huyen01$","Phuong","Le","SmartOsc", "" ,"Thanh Xuan, Nguyen Trai","Ha Noi","Ha Noi","1000","VN"},
			{"huyenhoang@gmail.com","Huyen01$","Phuong","Le","SmartOsc", "0317549393" ,"","Ha Noi","Ha Noi","1000","VN"},
			{"huyenhoang@gmail.com","Huyen01$","Phuong","Le","SmartOsc", "0317549393" ,"Thanh Xuan, Nguyen Trai","","Ha Noi","1000","VN"},
			{"huyenhoang@gmail.com","Huyen01$","Phuong","Le","SmartOsc", "0317549393" ,"Thanh Xuan, Nguyen Trai","Ha Noi","Ha Noi","","VN"},
			{"huyenhoang@gmail.com","Huyen01$","Phuong","Le","SmartOsc", "0317549393" ,"Thanh Xuan, Nguyen Trai","Ha Noi","Ha Noi","1000",""},
			{"huyenhoang@gmail.com","Huyen01$","Phuong","Le","SmartOsc", "0317549393" ,"Thanh Xuan, Nguyen Trai","Ha Noi","Ha Noi","1000","VN"}
			};
	}
	@DataProvider(name = "invalid_current_pass")
	public static Object[][] dataInalidAccountTest(){
		return new Object[][] {{"huyenhoang@gmail.com","Huyen01$","Huyen","Huyen01$$$$","Huyen01$$$$"}};
	}
	@DataProvider(name = "invalid_confirm_pass")
	public static Object[][] dataValidAccountTest(){
		return new Object[][] {{"huyenhoang@gmail.com","Huyen01$","Huyen01$$","Huyen01$$$$"}};
	}
}
