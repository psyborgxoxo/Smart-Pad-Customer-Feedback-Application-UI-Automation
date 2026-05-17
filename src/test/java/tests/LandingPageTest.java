package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class LandingPageTest extends BaseTest {

    private LandingPage landingPage;

    @BeforeMethod
    public void navigateToLandingPage() {
        landingPage = new LandingPage();
        landingPage.navigateTo("https://smartpad-customer-feedback.vercel.app/");
        WaitUtils.waitForPageLoad();
    }

    @Test
    public void verifyLandingPageElements() {
        AssertionUtils.assertTrue(
                landingPage.isAppHeaderVisible(),
                "Header should be visible on landing page");
        AssertionUtils.assertTrue(
                landingPage.isGetStartedButtonDisplayed(),
                "Get Started button should be visible on landing page");
    }
}