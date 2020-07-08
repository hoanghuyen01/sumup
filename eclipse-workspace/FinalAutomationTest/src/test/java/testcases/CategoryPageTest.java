package testcases;

import java.util.LinkedList;

import javax.sound.midi.Soundbank;

import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pages.CategoryPage;
import pages.HomePage;
import pages.SignInPage;

public class CategoryPageTest extends BaseTest{
	String MESSAGE_SUCCESS_COMPARISON_LIST = "You added product %s to the comparison list.";
	String MESAGE_SUCCESS_WISHLIST ="%s has been added to your Wish List. Click here to continue shopping.";
	String XPATH_MESSAGE_SUCCESS_ADD_TO_CART = "You added %s to your shopping cart.";
	CategoryPage categoryPage;
	HomePage homePage;
	SignInPage signInPage;
	@BeforeTest
	public void data() {
		categoryPage = new CategoryPage(driver);
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
	}
	@Test
	@Description("Test sorting product follow by price DESC")
	public void testSortingDes() {
		categoryPage.open();
		categoryPage.clickDescendingSorting();
		categoryPage.clickSortBy("price");
		LinkedList<Float> listPrices = new LinkedList<Float>();
		listPrices = categoryPage.getPriceListOfCategory(listPrices);
		Assert.assertEquals(categoryPage.comparePriceDesc(listPrices),true);
	}
	@Test 
	@Description("Test sorting product follow by price DESC")
	public void tetSortingAcs() {
		categoryPage.open();
		categoryPage.clickAscendingSorting();
		categoryPage.clickSortBy("price");
		LinkedList<Float> listPrices = new LinkedList<Float>();
		listPrices = categoryPage.getPriceListOfCategory(listPrices);
		Assert.assertEquals(categoryPage.comparePriceAcs(listPrices),true);
	}
//	@Test(dataProvider = "product_name")
//	@Description("Test adding products to compare")
	public void testAddProductCompare(String nameProduct) {
		categoryPage.open();
		System.out.println(nameProduct);
		categoryPage.clickAddToCompare(nameProduct);
		Assert.assertEquals(categoryPage.checkExistProductOnCompareList(),String.format(MESSAGE_SUCCESS_COMPARISON_LIST, nameProduct));
		Assert.assertEquals(categoryPage.nameProductOnCompareList(nameProduct),true);
	}
	@Test(dataProvider = "product_wishlist")
	@Description("Testing adding products to wishlist")
	public void testAddProductWishList(String email, String pass ,String nameProduct) {
		homePage.open().clickOnSignIn();
		signInPage.login(email, pass);
		categoryPage.open();
		categoryPage.clickAddToWishList(nameProduct);
		Assert.assertEquals(categoryPage.checkExistProductOnWishList(),String.format(MESAGE_SUCCESS_WISHLIST, nameProduct));
		captureScreenshot(nameProduct);
		Assert.assertEquals(categoryPage.nameProductOnWishList(nameProduct),true);
	}
	@Test(dataProvider = "product_name")
	@Description("Testing add product with option : Color-Black Size-S")
	public void addProductWithOptions(String nameProduct) {
		categoryPage.open();
		categoryPage.selectSSize(nameProduct);
		categoryPage.selectBlackColor(nameProduct);
		categoryPage.addToCart(nameProduct);
		Assert.assertEquals(categoryPage.addSuccessToCart(),String.format(XPATH_MESSAGE_SUCCESS_ADD_TO_CART, nameProduct));
		
	}
//	@Test
//	@Description("Combine filter  Size:S - Color: Black - Price:60-69.99")
	public void combineFilter() {
		categoryPage.open();
		categoryPage.filterSizeS();
		categoryPage.filterColorBlack();
		categoryPage.filterPrice6070();
		captureScreenshot("categoryFilter");
		
	}
	@DataProvider(name = "product_name")
	public static Object[][] invalidDataTest(){
		return new Object[][] {{"Proteus Fitness Jackshirt"}};
	}
	@DataProvider(name = "product_wishlist")
	public static Object[][] addProductToWishList(){
		return new Object[][] {{"huyenhoang@gmail.com","Huyen01$","Proteus Fitness Jackshirt"}};
	}
}
