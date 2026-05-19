package tests;

import base.BaseTest;
import config.ConfigurationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import utils.AssertionUtils;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandingPageTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LandingPageTest.class);

    private LandingPage landingPage;

    /**
     * Navigates to the landing page before each test.
     */
    @BeforeMethod
    public void navigateToLandingPage() {
        logger.info("Navigating to the landing page...");

        // Initialize the landing page object
        landingPage = new LandingPage();

        // Navigate to the base URL
        String baseUrl = ConfigurationManager.getBaseUrl();
        logger.info("Navigating to base URL: {}", baseUrl);
        landingPage.navigateTo(baseUrl);
        WaitUtils.waitForPageLoad();

        logger.info("Landing page loaded successfully.");
    }

    /**
     * Verifies that all expected elements are visible on the landing page.
     */
    @Test
    public void verifyLandingPageElements() {
        logger.info("Verifying landing page elements...");

        // Assert that the app header is visible
        AssertionUtils.assertTrue(
                landingPage.isAppHeaderVisible(),
                "Header should be visible on landing page");
        logger.info("Header is visible on the landing page.");

        // Assert that the 'Get Started' button is visible
        AssertionUtils.assertTrue(
                landingPage.isGetStartedButtonDisplayed(),
                "Get Started button should be visible on landing page");
        logger.info("'Get Started' button is visible on the landing page.");
    }
}
