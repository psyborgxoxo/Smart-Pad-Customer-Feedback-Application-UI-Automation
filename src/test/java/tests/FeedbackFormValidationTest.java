package tests;

import base.BaseTest;
import config.ConfigurationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.FeedbackPage;
import pages.LandingPage;
import pages.ProductPage;
import utils.AssertionUtils;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedbackFormValidationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackFormValidationTest.class);

    private LandingPage landingPage;
    private AuthPage authPage;
    private ProductPage productPage;
    private FeedbackPage feedbackPage;

    /**
     * Navigates to the feedback form before each test.
     */
    @BeforeMethod
    public void navigateToFeedbackForm() {
        logger.info("Navigating to the feedback form...");

        // Initialize page objects
        landingPage = new LandingPage();
        authPage = new AuthPage();
        productPage = new ProductPage();
        feedbackPage = new FeedbackPage();

        // Navigate to the landing page
        String baseUrl = ConfigurationManager.getBaseUrl();
        logger.info("Navigating to base URL: {}", baseUrl);
        landingPage.navigateTo(baseUrl);
        WaitUtils.waitForPageLoad();

        // Perform initial actions on the landing page
        logger.info("Clicking 'Get Started' button...");
        landingPage.clickGetStartedButton();

        AssertionUtils.assertTrue(landingPage.isGinElementVisible(),
                "Gin element should be visible after clicking 'Get Started'");
        logger.info("Gin element is visible. Clicking Gin element...");
        landingPage.clickGinElement();

        AssertionUtils.assertTrue(authPage.isContinueButtonVisible(),
                "Continue button should be visible after selecting Gin");
        logger.info("Continue button is visible. Clicking Continue button...");
        authPage.clickContinueButton();

        AssertionUtils.assertTrue(authPage.isAgeVerificationVisible(),
                "Age verification button should be visible");
        logger.info("Age verification button is visible. Clicking Age Verification button...");
        authPage.clickAgeVerification();

        AssertionUtils.assertTrue(productPage.isFirstProductVisible(),
                "First product should be visible after age verification");
        logger.info("First product is visible. Clicking first product...");
        productPage.clickFirstProduct();

        AssertionUtils.assertTrue(productPage.isShareFeedbackVisible(),
                "Share Feedback button should be visible");
        logger.info("Share Feedback button is visible. Clicking Share Feedback button...");
        productPage.clickShareFeedback();

        AssertionUtils.assertTrue(feedbackPage.isVisible(),
                "Feedback form should be visible");
        logger.info("Feedback form is visible.");
    }

    /**
     * Verifies validation when submitting the feedback form without filling it.
     */
    @Test
    public void verifyEmptyFormValidation() {
        logger.info("Testing empty form submission validation...");

        // Act: Submit the feedback form without filling it
        logger.info("Submitting feedback form without filling any fields...");
        feedbackPage.submitWithoutFilling();

        // Assert: Verify that the form remains visible
        AssertionUtils.assertTrue(feedbackPage.isVisible(),
                "Feedback form should remain visible when submitted empty");
        logger.info("Feedback form remains visible after empty submission.");
    }
}