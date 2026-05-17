package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.FeedbackPage;
import pages.LandingPage;
import pages.ProductPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class CompleteNavigationTest extends BaseTest {

    private LandingPage  landingPage;
    private AuthPage     authPage;
    private ProductPage  productPage;
    private FeedbackPage feedbackPage;

    @BeforeMethod
    public void navigateToLandingPage() {
        landingPage  = new LandingPage();
        authPage     = new AuthPage();
        productPage  = new ProductPage();
        feedbackPage = new FeedbackPage();

        landingPage.navigateTo("https://smartpad-customer-feedback.vercel.app/");
        WaitUtils.waitForPageLoad();

        AssertionUtils.assertTrue(landingPage.isAppHeaderVisible(),
                "App header should be visible on landing page");
    }

    @Test
    public void testCompleteNavigationFlow() {
        landingPage.clickGetStartedButton();

        AssertionUtils.assertTrue(landingPage.isGinElementVisible(),
                "Gin element should be visible after clicking Get Started");
        landingPage.clickGinElement();

        AssertionUtils.assertTrue(authPage.isContinueButtonVisible(),
                "Continue button should be visible after selecting Gin");
        authPage.clickContinueButton();

        AssertionUtils.assertTrue(authPage.isAgeVerificationVisible(),
                "Age verification button should be visible");
        authPage.clickAgeVerification();

        AssertionUtils.assertTrue(productPage.isFirstProductVisible(),
                "First product should be visible after age verification");
        productPage.clickFirstProduct();

        AssertionUtils.assertTrue(productPage.isShareFeedbackVisible(),
                "Share Feedback button should be visible");
        productPage.clickShareFeedback();

        AssertionUtils.assertTrue(feedbackPage.isVisible(),
                "Feedback form should be visible");

        feedbackPage.submitFeedback(
                "Test User",
                "testuser@example.com",
                "This is an automated test feedback. The product works well and meets expectations.");

        AssertionUtils.assertTrue(feedbackPage.isThankYouVisible(),
                "Thank You popup should be visible after submission");
    }
}