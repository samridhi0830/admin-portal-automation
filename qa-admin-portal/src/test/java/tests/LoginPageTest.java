package tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import Base.BaseTest;
import constants.AppConstants;



public class LoginPageTest extends BaseTest{

	@Test (priority=1)
	public void loginPageUrlTest() {
		String actURL= loginPage.getPageUrl();
		//Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL));
	}
	
	@Test (priority=2)
	public void doLoginTest() {
		dashPage=loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
	
		//Assert.assertTrue(otpPage.isEnterOtpExist());
	}
}

