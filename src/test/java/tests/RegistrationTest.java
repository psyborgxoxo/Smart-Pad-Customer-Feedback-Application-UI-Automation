package tests;

import base.BaseTest;
import config.ConfigurationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.RegistrationPage;
import utils.AssertionUtils;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    private LandingPage landingPage;
    private RegistrationPage registrationPage;

    /**
     * Navigates to the registration page before each test.
     */
    @BeforeMethod
    public void navigateToRegistrationPage() {
        logger.info("Navigating to the registration page...");

        // Initialize page objects
        landingPage = new LandingPage();
        registrationPage = new RegistrationPage();

        // Navigate to the landing page
        String baseUrl = ConfigurationManager.getBaseUrl();
        logger.info("Navigating to base URL: {}", baseUrl);
        landingPage.navigateTo(baseUrl);
        WaitUtils.waitForPageLoad();

        // Perform initial actions on the landing page
        logger.info("Clicking 'Get Started' button...");
        landingPage.clickGetStartedButton();

        AssertionUtils.assertTrue(landingPage.isGinElementVisible(), "Gin element should be visible");
        logger.info("Gin element is visible. Clicking Gin element...");
        landingPage.clickGinElement();

        logger.info("Landed on the registration page.");
    }

    /**
     * Verifies the registration flow by registering a new user.
     */
    @Test
    public void verifyRegistrationFlow() {
        logger.info("Testing registration flow...");

        // Generate a unique email for the test
        String email = "testuser" + System.currentTimeMillis() + "@mail.com";
        logger.info("Generated unique email for registration: {}", email);

        // Act: Register a new user
        logger.info("Registering new user...");
        registrationPage.registerNewUser(
                "Test User",
                email,
                "Password123"
        );

        // Assert: Verify that the user is navigated to the OTP verification screen
        AssertionUtils.assertTrue(
                registrationPage.isOtpVerificationVisible(),
                "User should be navigated to OTP verification screen after registration"
        );
        logger.info("User successfully navigated to OTP verification screen.");
    }
}