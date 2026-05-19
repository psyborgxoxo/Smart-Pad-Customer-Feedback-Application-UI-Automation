package tests;

import base.BaseTest;
import config.ConfigurationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;
import utils.AssertionUtils;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    private LandingPage landingPage;
    private LoginPage loginPage;

    /**
     * Navigates to the login page before each test.
     */
    @BeforeMethod
    public void navigateToLoginPage() {
        logger.info("Navigating to the login page...");

        // Initialize page objects
        landingPage = new LandingPage();
        loginPage = new LoginPage();

        // Navigate to the landing page
        String baseUrl = ConfigurationManager.getBaseUrl();
        logger.info("Navigating to base URL: {}", baseUrl);
        landingPage.navigateTo(baseUrl);
        WaitUtils.waitForPageLoad();

        // Perform initial actions on the landing page
        logger.info("Clicking 'Get Started' button...");
        landingPage.clickGetStartedButton();

        AssertionUtils.assertTrue(landingPage.isGinElementVisible(), "Gin option should be visible");
        logger.info("Gin element is visible. Clicking Gin element...");
        landingPage.clickGinElement();

        logger.info("Landed on the login page.");
    }

    /**
     * Verifies the login flow using email and password.
     */
    @Test
    public void verifyLoginWithEmailPassword() {
        logger.info("Testing login flow with email and password...");

        // Assert that the email, password, and login button are visible
        AssertionUtils.assertTrue(loginPage.isEmailFieldVisible(),
                "Email field should be visible");
        logger.info("Email field is visible.");

        AssertionUtils.assertTrue(loginPage.isPasswordFieldVisible(),
                "Password field should be visible");
        logger.info("Password field is visible.");

        AssertionUtils.assertTrue(loginPage.isLoginButtonVisible(),
                "Login button should be visible");
        logger.info("Login button is visible.");

        // Act: Perform login with credentials
        String email = "testuser@example.com";
        String password = "Test@123";
        logger.info("Logging in with email: {}, password: {}", email, "********");
        loginPage.loginWithCredentials(email, password);

        // Assert: Validate login success or failure (based on backend behavior)
        // Example: Check for a dashboard or error message after login
        logger.info("Login flow executed. Backend validation required for success/failure.");
    }
}
