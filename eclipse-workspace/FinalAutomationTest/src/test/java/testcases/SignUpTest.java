package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.SignUpPage;;

public class SignUpTest extends BaseTest {
	String  MESSAGE_REQUIRE = "This is a required field.";
	SignUpPage signUpPage;
	@BeforeTest
	public void data() {
		signUpPage = new SignUpPage(driver);
	}
	@Test(dataProvider = "data_test_Account")
	public void checkRequiredForm(String firstName,String lastName,String email,String pass,String confirmPass) {
		signUpPage.open();
		signUpPage.fillDataForm(firstName, lastName, email, pass, confirmPass);
		if (firstName == "") {
			Assert.assertEquals(signUpPage.requireFirstName(), MESSAGE_REQUIRE);
		}else if (lastName == "") {
			Assert.assertEquals(signUpPage.requireLastName(), MESSAGE_REQUIRE);
		}else if (pass == "") {
			Assert.assertEquals(signUpPage.requirePassword(), MESSAGE_REQUIRE);
		}else if (confirmPass == "") {
			Assert.assertEquals(signUpPage.requireConfirmPassword(), MESSAGE_REQUIRE);
		}else if (email == "") {
			Assert.assertEquals(signUpPage.requireEmail(), MESSAGE_REQUIRE);
		}
		
	}
	@DataProvider(name = "data_test_Account")
	public static Object[][] dataValidAccountTest(){
		return new Object[][] {
			{"","Hoang","lanhoang@gmail.com","Huyen01$","Huyen01$"},
			{"Lan","","lanhoang@gmail.com","Huyen01$","Huyen01$"},
			{"Lan","Hoang","","Huyen01$","Huyen01$"},
			{"Lan","Hoang","lanhoang@gmail.com","","Huyen01$"},
			{"Lan","Hoang","lanhoang@gmail.com","Huyen01$",""}
			};
	}
	
}
