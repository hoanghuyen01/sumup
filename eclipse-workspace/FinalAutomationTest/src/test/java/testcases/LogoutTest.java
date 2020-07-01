package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class LogoutTest extends BaseTest{
	HomePage homePage;
	SignInPage signInPage;
	@BeforeTest
	public void data() {
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
	}
	@Test (dataProvider = "data_test_valid_Account")
	public void canLogin(String email, String pass) {
		homePage.open().clickOnSignIn();
		signInPage.login(email, pass);
		Assert.assertEquals(signInPage.existWelcomeButton(),true);
	}
	
	@Test
	public void logout() {
		if(signInPage.existWelcomeButton()) {	
			Assert.assertEquals(signInPage.checkout(),true);
		}
	}
	
	@DataProvider(name = "data_test_valid_Account")
	public static Object[][] dataValidAccountTest(){
		return new Object[][] {{"huyenhoang@gmail.com","Huyen01$"}};
	}

}
