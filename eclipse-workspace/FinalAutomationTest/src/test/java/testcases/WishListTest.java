package testcases;

import java.util.Currency;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;
import pages.WishListPage;

public class WishListTest extends BaseTest{
	String XPATH_PRODUCT_DETAILS = "https://demo.smartosc.com/juno-jacket.html";
	String URL_WISH_LIST_INDEX ="https://demo.smartosc.com/wishlist/index/index/wishlist_id/";
	String MESSAGE_ADD_WISH_LIST_SUCCESS = "%s has been added to your Wish List. Click here to continue shopping.";
	String MESSAGE_REMOVE_SUCCESS ="%s has been removed from your Wish List.";
	String URL_WISHLIST = "https://demo.smartosc.com/wishlist/";
	WishListPage wishListPage;
	HomePage homePage;
	SignInPage signInPage;
	@BeforeTest
	public void data() {
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
		wishListPage = new WishListPage(driver);
	}
	@Test(dataProvider = "data_test_valid_Account")
	public void addProductToWishList(String email, String pass) {
		homePage.open();
		if (!signInPage.existWelcomeButton()) {
			homePage.clickOnSignIn();
			signInPage.login(email, pass);
		}
		driver.get(XPATH_PRODUCT_DETAILS);
		String nameProduct = homePage.getTitlePage();
		wishListPage.addProductToWishList();
		if (driver.getCurrentUrl().contains(URL_WISH_LIST_INDEX)) {
			String message = String.format(MESSAGE_ADD_WISH_LIST_SUCCESS, nameProduct);
			Assert.assertEquals(wishListPage.checkAddingProductWishList(), message);
		}
	}
	@Test(dataProvider = "data_test_valid_Account")
	public void checkButtonWithProduct(String email, String pass) {
		homePage.open();
		if (!signInPage.existWelcomeButton()) {
			homePage.clickOnSignIn();
			signInPage.login(email, pass);
		}
		driver.get(URL_WISHLIST);
		System.out.println(wishListPage.checkExistOfButton());
		System.out.println(wishListPage.checkExistProduct());
		Assert.assertEquals(wishListPage.checkExistOfButton(),wishListPage.checkExistProduct());
	}
	@Test(dataProvider = "name_product")
	public void removeProductFromWishList(String nameProduct) {
		Assert.assertEquals(wishListPage.clickRemoveWishList(nameProduct), String.format(MESSAGE_REMOVE_SUCCESS, nameProduct));
	}
	
	@DataProvider(name = "data_test_valid_Account")
	public static Object[][] dataValidAccountTest(){
		return new Object[][] {{"huyenhoang@gmail.com","Huyen01$"}};
	}
	@DataProvider(name = "name_product")
	public static Object[][] dataProductName(){
		return new Object[][] {{"Juno Jacket"}};
	}
}
