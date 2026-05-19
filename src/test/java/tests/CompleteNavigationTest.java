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

public class CompleteNavigationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CompleteNavigationTest.class);

    private LandingPage landingPage;
    private AuthPage authPage;
    private ProductPage productPage;
    private FeedbackPage feedbackPage;

    /**
     * Navigates to the landing page before each test.
     */
    @BeforeMethod
    public void navigateToLandingPage() {
        logger.info("Navigating to the landing page...");

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

        // Assert that the app header is visible
        AssertionUtils.assertTrue(landingPage.isAppHeaderVisible(),
                "App header should be visible on landing page");
        logger.info("App header is visible on the landing page.");
    }

    /**
     * Tests the complete navigation flow from landing page to feedback submission.
     */
    @Test
    public void testCompleteNavigationFlow() {
        logger.info("Testing complete navigation flow...");

        // Step 1: Click "Get Started" button
        logger.info("Clicking 'Get Started' button...");
        landingPage.clickGetStartedButton();

        // Assert that the Gin element is visible
        AssertionUtils.assertTrue(landingPage.isGinElementVisible(),
                "Gin element should be visible after clicking 'Get Started'");
        logger.info("Gin element is visible. Clicking Gin element...");
        landingPage.clickGinElement();

        // Step 2: Click "Continue without an account" button
        AssertionUtils.assertTrue(authPage.isContinueButtonVisible(),
                "Continue button should be visible after selecting Gin");
        logger.info("Continue button is visible. Clicking Continue button...");
        authPage.clickContinueButton();

        // Step 3: Perform age verification
        AssertionUtils.assertTrue(authPage.isAgeVerificationVisible(),
                "Age verification button should be visible");
        logger.info("Age verification button is visible. Clicking Age Verification button...");
        authPage.clickAgeVerification();

        // Step 4: Verify first product visibility and click it
        AssertionUtils.assertTrue(productPage.isFirstProductVisible(),
                "First product should be visible after age verification");
        logger.info("First product is visible. Clicking first product...");
        productPage.clickFirstProduct();

        // Step 5: Verify Share Feedback button visibility and click it
        AssertionUtils.assertTrue(productPage.isShareFeedbackVisible(),
                "Share Feedback button should be visible");
        logger.info("Share Feedback button is visible. Clicking Share Feedback button...");
        productPage.clickShareFeedback();

        // Step 6: Verify Feedback form visibility
        AssertionUtils.assertTrue(feedbackPage.isVisible(),
                "Feedback form should be visible");
        logger.info("Feedback form is visible.");

        // Step 7: Submit feedback
        logger.info("Submitting feedback...");
        feedbackPage.submitFeedback(
                "Test User",
                "testuser@example.com",
                "This is an automated test feedback. The product works well and meets expectations.");

        // Step 8: Verify Thank You popup visibility
        AssertionUtils.assertTrue(feedbackPage.isThankYouVisible(),
                "Thank You popup should be visible after submission");
        logger.info("Thank You popup is visible after feedback submission.");
    }
}