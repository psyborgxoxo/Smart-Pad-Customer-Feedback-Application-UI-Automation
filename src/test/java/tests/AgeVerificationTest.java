package tests;

import base.BaseTest;
import config.ConfigurationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.LandingPage;
import pages.ProductPage;
import utils.AssertionUtils;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgeVerificationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AgeVerificationTest.class);

    private LandingPage landingPage;
    private AuthPage authPage;
    private ProductPage productPage;

    /**
     * Navigates to the authentication page before each test.
     */
    @BeforeMethod
    public void navigateToAuthPage() {
        logger.info("Navigating to the authentication page...");

        // Initialize page objects
        landingPage = new LandingPage();
        authPage = new AuthPage();
        productPage = new ProductPage();

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
    }

    /**
     * Verifies the age verification flow.
     */
    @Test
    public void verifyAgeVerificationFlow() {
        logger.info("Verifying age verification flow...");

        // Assert that the age verification button is visible
        AssertionUtils.assertTrue(authPage.isAgeVerificationVisible(),
                "Age verification button should be visible");
        logger.info("Age verification button is visible. Clicking Age Verification button...");
        authPage.clickAgeVerification();

        // Assert that the user navigates to the product page after age verification
        AssertionUtils.assertTrue(productPage.isFirstProductVisible(),
                "User should navigate to the product page after age verification");
        logger.info("First product is visible on the product page.");
    }
}