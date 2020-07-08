package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CategoryPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.SignInPage;

public class CheckoutTest extends BaseTest {
	String SUCCESS_URL = "https://demo.smartosc.com/checkout/onepage/success/";
	HomePage homePage;
	SignInPage signInPage;
	CategoryPage categoryPage;
	CheckoutPage checkoutPage;
	@BeforeTest
	public void data() {
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
		categoryPage = new CategoryPage(driver);
		checkoutPage = new CheckoutPage(driver);
	}
	public void login(String email, String pass) {
		homePage.open().clickOnSignIn();
		signInPage.login(email, pass);
		driver.get("https://demo.smartosc.com/women/tops-women/jackets-women.html");
	}
	public void addProductToCart(String nameProduct) {
		categoryPage.selectBlackColor(nameProduct);
		categoryPage.selectSSize(nameProduct);
		categoryPage.addToCart(nameProduct);
	}
	public void goToCheckoutShipping() {
		driver.get("https://demo.smartosc.com/checkout/#shipping");
	}
	@Test(dataProvider = "data_test_checkout")
	public void checkout(String email,String pass, String nameProduct1, String nameProduct2, String nameProduct3) {
		login(email, pass);
		addProductToCart(nameProduct1);
		addProductToCart(nameProduct2);
		addProductToCart(nameProduct3);
		goToCheckoutShipping();
		checkoutPage.clickOnShippingMethodTableRate();
		checkoutPage.clickNextButton();
		checkoutPage.clickOnPaymentMethod();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, SUCCESS_URL);
		checkoutPage.checkNumberOrder();
		
	}
	@DataProvider(name = "data_test_checkout")
	public static Object[][] dataCheckoutTest(){
		return new Object[][] {{"huyenhoang@gmail.com","Huyen01$","Neve Studio Dance Jacket","Olivia 1/4 Zip Light Jacket","Nadia Elements Shell"}};
	}
}
