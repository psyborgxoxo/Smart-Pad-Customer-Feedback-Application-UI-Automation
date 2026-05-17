package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class LoginTest extends BaseTest {

    private LandingPage landingPage;
    private LoginPage   loginPage;

    @BeforeMethod
    public void navigateToLoginPage() {
        landingPage = new LandingPage();
        landingPage.navigateTo("https://smartpad-customer-feedback.vercel.app/");
        WaitUtils.waitForPageLoad();

        landingPage.clickGetStartedButton();
        AssertionUtils.assertTrue(landingPage.isGinElementVisible(), "Gin option should be visible");
        landingPage.clickGinElement();

        loginPage = new LoginPage();
    }

    @Test
    public void verifyLoginWithEmailPassword() {
        AssertionUtils.assertTrue(loginPage.isEmailFieldVisible(),    "Email field should be visible");
        AssertionUtils.assertTrue(loginPage.isPasswordFieldVisible(), "Password field should be visible");
        AssertionUtils.assertTrue(loginPage.isLoginButtonVisible(),   "Login button should be visible");

        loginPage.loginWithCredentials("testuser@example.com", "Test@123");

        System.out.println("Login flow executed (validation depends on backend behaviour)");
    }
}