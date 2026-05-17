package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.LandingPage;
import pages.ProductPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class AgeVerificationTest extends BaseTest {

    private LandingPage landingPage;
    private AuthPage    authPage;
    private ProductPage productPage;

    @BeforeMethod
    public void navigateToAuthPage() {
        landingPage = new LandingPage();
        authPage    = new AuthPage();
        productPage = new ProductPage();

        landingPage.navigateTo("https://smartpad-customer-feedback.vercel.app/");
        WaitUtils.waitForPageLoad();

        landingPage.clickGetStartedButton();
        AssertionUtils.assertTrue(landingPage.isGinElementVisible(),
                "Gin element should be visible after clicking Get Started");
        landingPage.clickGinElement();

        AssertionUtils.assertTrue(authPage.isContinueButtonVisible(),
                "Continue button should be visible after selecting Gin");
        authPage.clickContinueButton();
    }

    @Test
    public void verifyAgeVerificationFlow() {
        AssertionUtils.assertTrue(authPage.isAgeVerificationVisible(),
                "Age verification button should be visible");
        authPage.clickAgeVerification();

        AssertionUtils.assertTrue(productPage.isFirstProductVisible(),
                "User should navigate to product page after age verification");
    }
}