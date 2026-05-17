package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.RegistrationPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class RegistrationTest extends BaseTest {

    private LandingPage      landingPage;
    private RegistrationPage registrationPage;

    @BeforeMethod
    public void navigateToRegistrationPage() {
        landingPage      = new LandingPage();
        registrationPage = new RegistrationPage();

        landingPage.navigateTo("https://smartpad-customer-feedback.vercel.app/");
        WaitUtils.waitForPageLoad();

        landingPage.clickGetStartedButton();
        AssertionUtils.assertTrue(landingPage.isGinElementVisible(), "Gin should be visible");
        landingPage.clickGinElement();
    }

    @Test
    public void verifyRegistrationFlow() {
        registrationPage.registerNewUser(
                "Test User",
                "testuser" + System.currentTimeMillis() + "@mail.com",
                "Password123"
        );

        AssertionUtils.assertTrue(
                registrationPage.isOtpVerificationVisible(),
                "User should be navigated to OTP verification screen");
    }
}